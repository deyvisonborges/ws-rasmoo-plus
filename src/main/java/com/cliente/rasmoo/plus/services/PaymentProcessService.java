package com.cliente.rasmoo.plus.services;

import com.cliente.rasmoo.plus.dtos.PaymentProcessDto;
import com.cliente.rasmoo.plus.exceptions.BusinessException;
import com.cliente.rasmoo.plus.exceptions.NotFoundException;
import com.cliente.rasmoo.plus.integrations.MailIntegration;
import com.cliente.rasmoo.plus.integrations.WsRaspayIntegration;
import com.cliente.rasmoo.plus.mappers.UserPaymentInfoMapper;
import com.cliente.rasmoo.plus.mappers.wsraspay.CreditCardMapper;
import com.cliente.rasmoo.plus.mappers.wsraspay.CustomerMapper;
import com.cliente.rasmoo.plus.mappers.wsraspay.OrderMapper;
import com.cliente.rasmoo.plus.mappers.wsraspay.PaymentMapper;
import com.cliente.rasmoo.plus.repositories.UserPaymentInfoRepository;
import com.cliente.rasmoo.plus.repositories.UserRepository;
import com.cliente.rasmoo.plus.services.rules.PaymentProcessRules;

import java.util.Objects;

public class PaymentProcessService implements PaymentProcessRules {
    private final UserRepository userRepository;
    private final UserPaymentInfoRepository userPaymentInfoRepository;
    private final WsRaspayIntegration wsRaspayIntegration;
    private final MailIntegration mailIntegration;

    PaymentProcessService(
            UserRepository userRepository,
            UserPaymentInfoRepository userPaymentInfoRepository,
            WsRaspayIntegration wsRaspayIntegration,
            MailIntegration mailIntegration) {
        this.userRepository = userRepository;
        this.userPaymentInfoRepository = userPaymentInfoRepository;
        this.wsRaspayIntegration = wsRaspayIntegration;
        this.mailIntegration = mailIntegration;
    }
    @Override
    public Boolean process(PaymentProcessDto paymentProcessDto) {
        var userOpt = userRepository.findById(paymentProcessDto.getUserPaymentInfoDto().getId());

        if(userOpt.isEmpty()) {
            throw new NotFoundException("Usuário não encontrado");
        }

        var user = userOpt.get();

        if(Objects.nonNull(user.getSubscriptionTypeModel())) {
            throw new BusinessException("Pagamento não pode ser processado pois usário já possui assinatura.");
        }
        // cria ou atualiza usuario raspay
        var customerDto = wsRaspayIntegration.createCustomer(CustomerMapper.mapper(user));

        // cria o pedido de pagamento
        var orderDto = wsRaspayIntegration.createOrder(OrderMapper.mapper(customerDto.getId(), paymentProcessDto));

        // processa o pagamento
        var paymentDto = PaymentMapper.mapper(
                customerDto.getId(),
                orderDto.getId(),
                CreditCardMapper.mapper(
                        paymentProcessDto.getUserPaymentInfoDto(),
                        user.getCpf()));

        Boolean successPayment = wsRaspayIntegration.processPayment(paymentDto);

        if(Boolean.TRUE.equals(successPayment)) {
            // salvar informações de pagamento
            var userPaymentInfo = UserPaymentInfoMapper.mapper(paymentProcessDto.getUserPaymentInfoDto(), user);
            userPaymentInfoRepository.save(userPaymentInfo);
            mailIntegration.send(user.getEmail(), "Login: "+user.getEmail()+"Senha: aluno123", "Acesso liberado");
        }

        // enviar email de criacao de conta

        return null;
    }
}

