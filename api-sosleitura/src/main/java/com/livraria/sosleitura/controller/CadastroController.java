package com.livraria.sosleitura.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RequestMapping(path = "/cadastro")
@RestController
public class CadastroController {
    Logger logger = LoggerFactory.getLogger(CadastroController.class);

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> cadastroUsuario(@RequestBody  Map<String,Object> campos){
        logger.info("login: "+campos.get("login"));
        return ResponseEntity.ok().build();
    }
}
