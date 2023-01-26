package com.cliente.rasmoo.plus.mappers.wsraspay;

import com.cliente.rasmoo.plus.dtos.UserPaymentInfoDto;
import com.cliente.rasmoo.plus.dtos.wsraspay.CreditCardDto;

public class CreditCardMapper {
    public static CreditCardDto mapper(UserPaymentInfoDto userPaymentInfoDto, String documentNumber) {
        return CreditCardDto.builder()
            .documentNumber(documentNumber)
            .ccv(Long.parseLong(userPaymentInfoDto.getCardSecurityCode()))
            .installment(userPaymentInfoDto.getInstallments())
            .month(userPaymentInfoDto.getCardExpirationMonth())
            .number(userPaymentInfoDto.getCardNumber())
            .year(userPaymentInfoDto.getCardExpirationYear())
            .build();
    }
}
