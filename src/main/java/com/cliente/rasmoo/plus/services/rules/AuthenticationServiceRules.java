package com.cliente.rasmoo.plus.services.rules;

import com.cliente.rasmoo.plus.dtos.LoginDto;
import com.cliente.rasmoo.plus.dtos.TokenDto;

public interface AuthenticationServiceRules {
    TokenDto auth(LoginDto dto);
}
