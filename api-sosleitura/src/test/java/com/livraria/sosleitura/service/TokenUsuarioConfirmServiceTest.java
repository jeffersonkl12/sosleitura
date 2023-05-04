package com.livraria.sosleitura.service;

import com.livraria.sosleitura.model.Usuario;
import com.livraria.sosleitura.repository.TokenUsuarioConfirmRepository;
import com.livraria.sosleitura.security.TokenUsuarioConfirm;
import com.livraria.sosleitura.security.TokenUsuarioConfirmService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class TokenUsuarioConfirmServiceTest {


    @Test
    public void testTokenValido(){
        TokenUsuarioConfirm token = new TokenUsuarioConfirm();
        token.setToken(UUID.randomUUID().toString());
        token.setUsuario(Mockito.mock(Usuario.class));
        token.setData(LocalDateTime.of(2023,4,25,10,28,0));

        TokenUsuarioConfirmRepository confirmRepository  = Mockito.mock(TokenUsuarioConfirmRepository.class);
        Mockito.when(confirmRepository.existsByToken(Mockito.anyString())).thenReturn(true);
        Mockito.when(confirmRepository.findByToken(Mockito.anyString())).thenReturn(token);

        TokenUsuarioConfirmService service = new TokenUsuarioConfirmService();
        service.setRepository(confirmRepository);

        Assertions.assertTrue(service.validaToken(token.getToken()));
    }
}
