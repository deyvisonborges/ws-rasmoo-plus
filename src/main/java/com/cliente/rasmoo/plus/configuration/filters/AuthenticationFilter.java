package com.cliente.rasmoo.plus.configuration.filters;

import com.cliente.rasmoo.plus.services.TokenServiceImpl;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;


public class AuthenticationFilter extends OncePerRequestFilter {
    private TokenServiceImpl tokenService;

    public AuthenticationFilter(
            TokenServiceImpl tokenService
    ) {
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getBearerToken(request);
        if(tokenService.isValid(token)) {

        }
        filterChain.doFilter(request, response);
    }

    private String getBearerToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if (Objects.isNull(token) || !token.startsWith("Bearer")) {
            return null;
        }
        return token.substring(7, token.length());
    }
}
