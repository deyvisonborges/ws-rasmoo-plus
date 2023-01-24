package com.cliente.rasmoo.plus.mappers.wsraspay;

import com.cliente.rasmoo.plus.dtos.wsraspay.CustomerDto;
import com.cliente.rasmoo.plus.models.UserModel;

public class CustomerMapper {
    public static CustomerDto mapper(UserModel user) {
        var fullName = user.getName().split(" ");
        var firstName = fullName[0];
        var lastName = fullName.length > 1 ? fullName[fullName.length - 1] : "";

        return CustomerDto.builder()
            .cpf(user.getCpf())
            .email(user.getEmail())
            .firstName(firstName)
            .lastName(lastName)
            .build();
    }
}
