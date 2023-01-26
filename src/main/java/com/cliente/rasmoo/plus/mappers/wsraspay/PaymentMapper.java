package com.cliente.rasmoo.plus.mappers.wsraspay;

import com.cliente.rasmoo.plus.dtos.wsraspay.CreditCardDto;
import com.cliente.rasmoo.plus.dtos.wsraspay.PaymentDto;

public class PaymentMapper {
    public static PaymentDto mapper(String customerId, String orderId, CreditCardDto creditCardDto) {
        return PaymentDto.builder()
            .customerId(customerId)
            .orderId(orderId)
            .creditCard(creditCardDto)
            .build();
    }
}
