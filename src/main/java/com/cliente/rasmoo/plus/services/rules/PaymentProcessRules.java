package com.cliente.rasmoo.plus.services.rules;

import com.cliente.rasmoo.plus.dtos.PaymentProcessDto;
import com.cliente.rasmoo.plus.dtos.wsraspay.PaymentDto;

public interface PaymentProcessRules {
    Boolean process(PaymentProcessDto paymentProcessDto);
}
