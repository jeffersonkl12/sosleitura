package com.livraria.sosleitura.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
public class AutorExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void NosuchElementAutorTest() throws Exception {
      String response = mockMvc.perform(MockMvcRequestBuilders
                .get("http://localhost:8080/sosleitura/api/v1/autor/1")
                .contextPath("/sosleitura"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn()
                .getResponse().getContentAsString();

        Assertions.assertNotNull(response);

        System.out.println(response);
    }
}
