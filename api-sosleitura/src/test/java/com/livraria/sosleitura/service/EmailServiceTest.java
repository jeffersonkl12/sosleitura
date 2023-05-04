package com.livraria.sosleitura.service;

import jakarta.mail.MessagingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureMockMvc
@SpringBootTest
public class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    @Test
    @DisplayName(value = "testando envio de email")
    public void senEmailTest() throws MessagingException, IOException {
        String msg = "<p>Bem vindo ao Sosleitura, ficamos muito feliz peloseus interesse em se dadastrar no nosso sistema!</p>\n" +
                "        <p>Clique no email asseguir para ativar sua conta! ${email}</p>";
        emailService.sendEmail("jeffersonkl99@gmail.com","testando emial com html",msg);
    }
}
