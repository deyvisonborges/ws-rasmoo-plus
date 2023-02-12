package com.cliente.rasmoo.plus.services;

import com.cliente.rasmoo.plus.dtos.PaymentProcessDto;
import com.cliente.rasmoo.plus.exceptions.BusinessException;
import com.cliente.rasmoo.plus.exceptions.NotFoundException;
import com.cliente.rasmoo.plus.integrations.WsRaspayIntegration;
import com.cliente.rasmoo.plus.mappers.UserPaymentInfoMapper;
import com.cliente.rasmoo.plus.mappers.wsraspay.CreditCardMapper;
import com.cliente.rasmoo.plus.mappers.wsraspay.CustomerMapper;
import com.cliente.rasmoo.plus.mappers.wsraspay.OrderMapper;
import com.cliente.rasmoo.plus.mappers.wsraspay.PaymentMapper;
import com.cliente.rasmoo.plus.models.UserCredentials;
import com.cliente.rasmoo.plus.models.UserTypeModel;
import com.cliente.rasmoo.plus.repositories.UserDetailsRepository;
import com.cliente.rasmoo.plus.repositories.UserPaymentInfoRepository;
import com.cliente.rasmoo.plus.repositories.UserRepository;
import com.cliente.rasmoo.plus.services.rules.PaymentProcessRules;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PaymentProcessService implements PaymentProcessRules {
    private final Long ALUNO = 3L;
    private final UserRepository userRepository;
    private final UserPaymentInfoRepository userPaymentInfoRepository;
    private final WsRaspayIntegration wsRaspayIntegration;
    private final UserDetailsRepository userDetailsRepository;

    public PaymentProcessService(UserRepository userRepository, UserPaymentInfoRepository userPaymentInfoRepository, WsRaspayIntegration wsRaspayIntegration, UserDetailsRepository userDetailsRepository) {
        this.userRepository = userRepository;
        this.userPaymentInfoRepository = userPaymentInfoRepository;
        this.wsRaspayIntegration = wsRaspayIntegration;
        this.userDetailsRepository = userDetailsRepository;
    }


    @Override
    public Boolean process(PaymentProcessDto paymentProcessDto) {
        var userOpt = userRepository.findById(paymentProcessDto.getUserPaymentInfoDto().getId());

        if (userOpt.isEmpty()) {
            throw new NotFoundException("Usuário não encontrado");
        }

        var user = userOpt.get();

        if (Objects.nonNull(user.getSubscriptionTypeModel())) {
            throw new BusinessException("Pagamento não pode ser processado pois usário já possui assinatura.");
        }
        var customerDto = wsRaspayIntegration.createCustomer(CustomerMapper.mapper(user));
        var orderDto = wsRaspayIntegration.createOrder(OrderMapper.mapper(customerDto.getId(), paymentProcessDto));
        var paymentDto = PaymentMapper.mapper(customerDto.getId(), orderDto.getId(), CreditCardMapper.mapper(paymentProcessDto.getUserPaymentInfoDto(), user.getCpf()));

        Boolean successPayment = wsRaspayIntegration.processPayment(paymentDto);

        if (Boolean.TRUE.equals(successPayment)) {
            var userPaymentInfo = UserPaymentInfoMapper.mapper(paymentProcessDto.getUserPaymentInfoDto(), user);
            userPaymentInfoRepository.save(userPaymentInfo);
            UserTypeModel userTypeModel = new UserTypeModel();
            UserCredentials userCredentials = new UserCredentials(null, user.getName(), null, userTypeModel);
            userDetailsRepository.save(userCredentials);
            return true;
        }
        return false;
    }
}

