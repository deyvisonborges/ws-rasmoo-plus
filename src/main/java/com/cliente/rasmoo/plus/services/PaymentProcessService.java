package com.cliente.rasmoo.plus.services;

import com.cliente.rasmoo.plus.dtos.PaymentProcessDto;
import com.cliente.rasmoo.plus.exceptions.BusinessException;
import com.cliente.rasmoo.plus.exceptions.NotFoundException;
import com.cliente.rasmoo.plus.repositories.UserRepository;
import com.cliente.rasmoo.plus.services.rules.PaymentProcessRules;

import java.util.Objects;

public class PaymentProcessService implements PaymentProcessRules {
    private final UserRepository userRepository;
    PaymentProcessService(UserRepository userRepository) {
        this.userRepository = userRepository;
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

        return null;
    }
}

