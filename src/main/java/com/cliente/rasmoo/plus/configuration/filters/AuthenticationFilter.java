package com.cliente.rasmoo.plus.configuration.filters;

import com.cliente.rasmoo.plus.exceptions.NotFoundException;
import com.cliente.rasmoo.plus.models.UserCredentials;
import com.cliente.rasmoo.plus.repositories.UserDetailsRepository;
import com.cliente.rasmoo.plus.services.TokenServiceImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;


public class AuthenticationFilter extends OncePerRequestFilter {
    private TokenServiceImpl tokenService;
    private UserDetailsRepository userDetailsRepository;

    public AuthenticationFilter(TokenServiceImpl tokenService, UserDetailsRepository userDetailsRepository) {
        this.tokenService = tokenService;
        this.userDetailsRepository = userDetailsRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getBearerToken(request);
        if (tokenService.isValid(token)) {
            authByToken(token);
        }
        filterChain.doFilter(request, response);
    }

    private void authByToken(String token) {
        Long userId = tokenService.getUserId(token);
        var userOpt = userDetailsRepository.findById(userId);

        if (userOpt.isEmpty()) {
            throw new NotFoundException("usuário não encontrado");
        }

        UserCredentials userCredentials = userOpt.get();

        var userPassAuth = new UsernamePasswordAuthenticationToken(userCredentials, null, userCredentials.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(userPassAuth);
    }


    private String getBearerToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if (Objects.isNull(token) || !token.startsWith("Bearer")) {
            return null;
        }
        return token.substring(7, token.length());
    }
}
