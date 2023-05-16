package com.livraria.sosleitura.controller;


import com.livraria.sosleitura.service.JwtService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
public class JwtServiceTest {
    @Autowired
    private JwtService jwtService;
    private static final String login = "jeffersonkl99@gmail.com";
    @Test
    public void deleteTokenByIdTest() throws NoSuchAlgorithmException {
        String token = jwtService.codeJwtTokenRefresh(new HashMap<>(),login);
        jwtService.deleteTokenRefreshByJti(token);
        Assertions.assertFalse(jwtService.existTokenById(token));
        System.out.println(token);
    }
}
