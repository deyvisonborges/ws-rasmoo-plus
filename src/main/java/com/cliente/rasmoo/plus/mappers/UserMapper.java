package com.cliente.rasmoo.plus.mappers;

import com.cliente.rasmoo.plus.dtos.UserDto;
import com.cliente.rasmoo.plus.models.SubscriptionTypeModel;
import com.cliente.rasmoo.plus.models.UserModel;
import com.cliente.rasmoo.plus.models.UserTypeModel;

public class UserMapper {
    private UserMapper() {
        throw new IllegalStateException("Você não pode instanciar essa classe de utilitário");
    }

    public static UserModel fromDtoToEntity(UserDto userDto, UserTypeModel userType, SubscriptionTypeModel subscriptionTypeModel) {
        return UserModel.builder()
            .id(userDto.getId())
            .name(userDto.getName())
            .cpf(userDto.getCpf())
            .email(userDto.getEmail())
            .phone(userDto.getPhone())
            .dtSubscription(userDto.getDtSubscription())
            .dtExpiration(userDto.getDtExpiration())
            .userTypeModel(userType)
            .subscriptionTypeModel(subscriptionTypeModel)
            .build();
    }
}
