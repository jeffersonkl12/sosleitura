package com.livraria.sosleitura.event;

import com.livraria.sosleitura.model.Usuario;
import com.livraria.sosleitura.repository.TokenUsuarioConfirmRepository;
import com.livraria.sosleitura.security.TokenUsuarioConfirm;
import com.livraria.sosleitura.security.TokenUsuarioConfirmService;
import com.livraria.sosleitura.service.EmailService;
import jakarta.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;


@Component
public class HandlerCadastroListener implements ApplicationListener<UserCadastroEvent> {

    Logger logger = LoggerFactory.getLogger(HandlerCadastroListener.class);
    @Autowired
    private TokenUsuarioConfirmService tokenService;
    @Autowired
    private EmailService emailService;
    @Override
    public void onApplicationEvent(UserCadastroEvent event) {

        Usuario usuario = event.getUsuario();
        TokenUsuarioConfirm tokenUsuarioConfirm = new TokenUsuarioConfirm();
        tokenUsuarioConfirm.setUsuario(usuario);
        tokenUsuarioConfirm.setToken(UUID.randomUUID().toString());
        tokenUsuarioConfirm.setData(LocalDateTime.now());

        try {
            tokenUsuarioConfirm = tokenService.salvaToken(tokenUsuarioConfirm);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        String url = "http://localhost:8080/sosleitura/email/ativa-email?token=".concat(tokenUsuarioConfirm.getToken());

        StringBuilder conteudo = new StringBuilder();
        conteudo.append("<p>Obrigado por se cadastrar no site SOSleitura, ficamos muito felizes por isso</p>");
        conteudo.append("<p>por favor clique no seguinte link para confirmar seu cadastro: </p>");
        conteudo.append(url);

        try {
            emailService.sendEmail(usuario.getLogin(),"Confirmação do cadastro",conteudo.toString());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
