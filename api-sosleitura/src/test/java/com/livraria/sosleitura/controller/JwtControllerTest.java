package com.livraria.sosleitura.controller;

import com.livraria.sosleitura.service.JwtService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureMockMvc
@SpringBootTest
public class JwtControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JwtService jwtService;

    private static final String login = "jeffersonkl99@gmail.com";

    @DisplayName(value = "adquirindo tokenrefresh")
    @Test
    public void validTokenRefreshTest() throws Exception {
        String tokenRefresh = jwtService.codeJwtTokenRefresh(new HashMap<>(),login);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","Bearer ".concat(tokenRefresh));
        System.out.println("antigo token: "+tokenRefresh);
        String novoTokeRefresh = mockMvc.perform(MockMvcRequestBuilders
                                                .post("http://localhost:8080/sosleitura/token/refresh")
                                                .contextPath("/sosleitura")
                                                .headers(headers))
                                                .andExpect(MockMvcResultMatchers.status().isOk())
                                                .andReturn().getResponse().getContentAsString();

        Assertions.assertNotNull(novoTokeRefresh);
        System.out.println("novo token: "+novoTokeRefresh);
    }

}
