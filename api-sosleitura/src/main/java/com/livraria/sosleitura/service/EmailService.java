package com.livraria.sosleitura.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private MailSender mailSender;

    public void senEmail(String to, String msg){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("testando email");
        message.setText(msg);
        mailSender.send(message);
    }
}
