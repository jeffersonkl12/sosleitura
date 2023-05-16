package com.livraria.sosleitura.controller;

import com.livraria.sosleitura.model.StatusUser;
import com.livraria.sosleitura.model.Usuario;
import com.livraria.sosleitura.security.TokenUsuarioConfirm;
import com.livraria.sosleitura.security.TokenUsuarioConfirmService;
import com.livraria.sosleitura.service.JwtService;
import com.livraria.sosleitura.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RequestMapping(path = "/")
@RestController
public class DefaultController {

    @Autowired
    private TokenUsuarioConfirmService tokenService;
    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping(path = "ativa-email",params = "token")
    public ResponseEntity<?> ativaEmail(@RequestParam(name = "token") String token) throws Exception {
        if(tokenService.validaToken(token)){
            TokenUsuarioConfirm tokenUser = tokenService.findByToken(token);
            tokenUser.setData(LocalDateTime.of(1000,1,1, 0,0,0));
            tokenService.salvaToken(tokenUser);

            Usuario usuario = usuarioService.buscaUsuarioById(tokenUser.getUsuario().getId());
            usuario.setStatusUser(StatusUser.ATIVADO);
            usuario = usuarioService.salvaUsuario(usuario);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
