package com.cliente.rasmoo.plus.services;

import com.cliente.rasmoo.plus.dtos.PaymentProcessDto;
import com.cliente.rasmoo.plus.dtos.UserPaymentInfoDto;
import com.cliente.rasmoo.plus.exceptions.BusinessException;
import com.cliente.rasmoo.plus.exceptions.NotFoundException;
import com.cliente.rasmoo.plus.mappers.UserPaymentInfoMapper;
import com.cliente.rasmoo.plus.repositories.UserPaymentInfoRepository;
import com.cliente.rasmoo.plus.repositories.UserRepository;
import com.cliente.rasmoo.plus.services.rules.PaymentProcessRules;
import jdk.jfr.Description;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

public class PaymentProcessService implements PaymentProcessRules {
    private final UserRepository userRepository;
    private final UserPaymentInfoRepository userPaymentInfoRepository;

    PaymentProcessService(UserRepository userRepository, UserPaymentInfoRepository userPaymentInfoRepository) {
        this.userRepository = userRepository;
        this.userPaymentInfoRepository = userPaymentInfoRepository;
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

        // salvar informações de pagamento
        var userPaymentInfo = UserPaymentInfoMapper.mapper(paymentProcessDto.getUserPaymentInfoDto(), user);
        userPaymentInfoRepository.save(userPaymentInfo);

        return null;
    }
}

