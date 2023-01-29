package com.cliente.rasmoo.plus.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .authorizeRequests()
            .antMatchers(HttpMethod.GET, "/subscription-type")
                .permitAll()
            .antMatchers(HttpMethod.GET, "/subiscription-type/*")
                .permitAll()
                .anyRequest().authenticated();
        return httpSecurity.build();
    }
}
