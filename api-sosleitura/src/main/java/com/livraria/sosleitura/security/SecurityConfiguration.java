package com.livraria.sosleitura.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoderConfig(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsService userDetailsServiceCustom(){
        return new UserDetailServiceCustom();
    }
    @Bean
    public AuthenticationProvider daoAuthenticationProviderCustom(){
       DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
       daoAuthenticationProvider.setUserDetailsService(userDetailsServiceCustom());
       daoAuthenticationProvider.setPasswordEncoder(passwordEncoderConfig());

       return daoAuthenticationProvider;
    }
}
