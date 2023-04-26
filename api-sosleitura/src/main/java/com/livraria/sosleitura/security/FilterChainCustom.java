package com.livraria.sosleitura.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
public class FilterChainCustom {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((request)->
            request.requestMatchers("/api/v1/autor/**").permitAll()
                    .anyRequest().denyAll()
                 )
                .csrf().disable();

        return http.build();
    }
}
