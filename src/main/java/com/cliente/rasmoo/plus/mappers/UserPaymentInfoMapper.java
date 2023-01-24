package com.cliente.rasmoo.plus.mappers;

import com.cliente.rasmoo.plus.dtos.UserPaymentInfoDto;
import com.cliente.rasmoo.plus.models.UserModel;
import com.cliente.rasmoo.plus.models.UserPaymentInfoModel;

public class UserPaymentInfoMapper {
    public static UserPaymentInfoModel mapper(UserPaymentInfoDto dto, UserModel user) {
        return UserPaymentInfoModel
            .builder()
            .id(dto.getId())
            .cardNumber(dto.getCardNumber())
            .cardExpirationMonth(dto.getCardExpirationMonth())
            .cardExpirationYear(dto.getCardExpirationYear())
            .cardSecurityCode((dto.getCardSecurityCode()))
            .price(dto.getPrice())
            .dtPayment(dto.getDtPayment())
            .user(user)
            .build();
    }
}
