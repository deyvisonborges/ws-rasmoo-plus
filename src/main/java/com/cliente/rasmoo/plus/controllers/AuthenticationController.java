package com.cliente.rasmoo.plus.controllers;

import com.cliente.rasmoo.plus.dtos.LoginDto;
import com.cliente.rasmoo.plus.exceptions.BadRequestException;
import com.cliente.rasmoo.plus.services.TokenServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private AuthenticationManager authenticationManager;
    private TokenServiceImpl tokenService;

    @PostMapping
    public ResponseEntity<String> auth(@RequestBody @Valid LoginDto dto) {
        var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());

        try {
            Authentication _auth = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            String token = tokenService.getToken(_auth);
            return ResponseEntity.status(HttpStatus.OK).body(token);
        } catch(Exception e) {
            throw new BadRequestException("Erro ao formatar token "+e.getMessage());
        }
    }
}
