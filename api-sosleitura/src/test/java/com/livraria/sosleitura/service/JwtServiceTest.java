package com.livraria.sosleitura.service;

import org.junit.jupiter.api.*;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class JwtServiceTest {


    private JwtService jwtService;

    @BeforeAll
    public void init(){
        jwtService = new JwtService();
    }


    @Test
    @DisplayName(value = "teste code do token")
    public void testCodeJwtToken() throws NoSuchAlgorithmException {
        String token = jwtService.codeJwtToken(new HashMap<>(),"jefferson");

        Assertions.assertNotNull(token);
        System.out.println(token);
    }

    @DisplayName(value = "testando pega sub do token")
    @Test
    public void testGetSubjectToken() throws NoSuchAlgorithmException {
        String token = jwtService.codeJwtToken(new HashMap<>(),"jefferson");
        String sub = jwtService.getSubjectFromToken(token);

        Assertions.assertNotNull(sub);
        System.out.println(sub);
    }

}
