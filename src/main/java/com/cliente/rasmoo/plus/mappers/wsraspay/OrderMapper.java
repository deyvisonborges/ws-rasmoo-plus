package com.cliente.rasmoo.plus.mappers.wsraspay;

import com.cliente.rasmoo.plus.dtos.PaymentProcessDto;
import com.cliente.rasmoo.plus.dtos.wsraspay.CustomerDto;
import com.cliente.rasmoo.plus.dtos.wsraspay.OrderDto;

public class OrderMapper {
    public static OrderDto mapper(String customerId, PaymentProcessDto paymentProcessDto) {
        return OrderDto.builder()
            .customerId(customerId)
            .productAcronym(paymentProcessDto.getProductKey())
            .discount(paymentProcessDto.getDiscount())
            .build();
    }
}
