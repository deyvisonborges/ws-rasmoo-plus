package com.cliente.rasmoo.plus.services;

import com.cliente.rasmoo.plus.models.UserCredentials;
import com.cliente.rasmoo.plus.services.rules.TokenServiceRules;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenServiceImpl implements TokenServiceRules {

    @Value("${webservices.rasplus.jwt.expiration}")
    private String expiration;

    @Value("${webservices.rasplus.jwt.secret}")
    private String secret;

    public String getToken(Authentication auth) {
        UserCredentials user = (UserCredentials) auth.getPrincipal();
        Date today = new Date();
        Date expirationDate = new Date(today.getTime() + Long.parseLong(expiration));

        return Jwts.builder()
                .setIssuer("API Rasmoo Plus")
                .setSubject(user.getId().toString())
                .setIssuedAt(today)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    @Override
    public Boolean isValid(String token) {
        try {
            getClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Long getUserId(String token) {
        try {
           Jws<String> claims = getClaimsJws(token);
            return Long.parseLong(claims.getBody());
        } catch (Exception e) {
            throw e;
        }
    }

    private Jws<String> getClaimsJws(String token) {
        return Jwts.parser().setSigningKey(secret).parsePlaintextJws(token);
    }
}
