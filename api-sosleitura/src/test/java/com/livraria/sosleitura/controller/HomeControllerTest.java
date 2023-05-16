package com.livraria.sosleitura.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureMockMvc
@SpringBootTest
public class HomeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void cadastroUsuarioTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("http://localhost:8080/sosleitura/home/cadastro")
                .contextPath("/sosleitura")
                .param("login","jeffersonkl99@gmail.com")
                .param("password","1234")
                .param("nome","jefferson"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void validaEmailTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("http://localhost:8080/sosleitura/ativa-email")
                .contextPath("/sosleitura")
                .param("token","e08689b2-c73b-4e44-8553-f59a0b8f1447"))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
    @DisplayName(value = "teste login")
    @Test
    public void testLogin() throws Exception {
     String status =  mockMvc.perform(MockMvcRequestBuilders
                .post("http://localhost:8080/sosleitura/home/login")
                .contextPath("/sosleitura")
                .param("login","jeffersonkl99@gmail.com")
                .param("password","1234"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

     Assertions.assertNotNull(status);
     System.out.println(status);

    }
}
