package com.livraria.sosleitura;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ReadFileHmtlTest {

    @Test
    public void lerArquivoHtmlTest() throws IOException {
        InputStream file = getClass().getClassLoader().getResourceAsStream("static/email.html");
        StringBuilder texto = new StringBuilder();
        Reader reader = new BufferedReader(new InputStreamReader(file,
                Charset.forName(StandardCharsets.UTF_8.name())));
        int aux = 0;
        while ((aux = reader.read()) != -1){
            texto.append((char)aux);
        }
        String convert =  texto.toString().replace("${email}","jefferson@mail.com");
        System.out.println(convert);
    }
}
