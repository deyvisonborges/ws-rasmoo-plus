package com.cliente.rasmoo.plus.services.rules;

import org.springframework.security.core.Authentication;

public interface TokenServiceRules {

    String getToken(Authentication auth);
    Boolean isValid(String token);
    Long getUserId(String token);
}
