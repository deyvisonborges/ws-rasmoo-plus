package com.cliente.rasmoo.plus.services.rules;

import com.cliente.rasmoo.plus.dtos.UserDto;
import com.cliente.rasmoo.plus.models.UserModel;

public interface UserServiceRules {
    UserModel create(UserDto dto);
}
