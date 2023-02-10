package com.cliente.rasmoo.plus.services;

import com.cliente.rasmoo.plus.dtos.LoginDto;
import com.cliente.rasmoo.plus.dtos.TokenDto;
import com.cliente.rasmoo.plus.exceptions.BadRequestException;
import com.cliente.rasmoo.plus.services.rules.AuthenticationServiceRules;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public class AuthenticationService implements AuthenticationServiceRules {

    private AuthenticationManager authenticationManager;
    private TokenServiceImpl tokenService;

    @Override
    public TokenDto auth(LoginDto dto) {
        try {
            var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
            Authentication _auth = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            String token = tokenService.getToken(_auth);

            return TokenDto.builder().token(token).type("Bearer").build();
        } catch (Exception e) {
            throw new BadRequestException("Erro ao formatar token " + e.getMessage());
        }
    }
}
