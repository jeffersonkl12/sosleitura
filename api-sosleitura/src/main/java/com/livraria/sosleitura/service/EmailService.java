package com.livraria.sosleitura.service;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Service
public class EmailService {

    @Value(value = "${spring.mail.username}")
    private String destinatario;
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String to, String titulo, String msg) throws MessagingException, IOException {
        MimeMessage message = javaMailSender.createMimeMessage();

        String response = loadHtmlFile();
        response = response.replace("${email}",to);
        response = response.replace("${content}",msg);
        message.setFrom(new InternetAddress(destinatario));
        message.setRecipients(Message.RecipientType.TO,to);
        message.setSubject(titulo);
        message.setContent(response,"text/html; charset=utf-8");

        javaMailSender.send(message);
    }

    public String loadHtmlFile() throws IOException {
        InputStream file = getClass().getClassLoader().getResourceAsStream("static/email.html");
        StringBuilder texto = new StringBuilder();
        Reader reader = new BufferedReader(new InputStreamReader(file,
                Charset.forName(StandardCharsets.UTF_8.name())));
        int aux = 0;
        while ((aux = reader.read()) != -1){
            texto.append((char)aux);
        }
        return texto.toString();
    }
}
