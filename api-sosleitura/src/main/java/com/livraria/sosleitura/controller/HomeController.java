package com.livraria.sosleitura.controller;

import com.livraria.sosleitura.event.UserCadastroEvent;
import com.livraria.sosleitura.model.Role;
import com.livraria.sosleitura.model.StatusUser;
import com.livraria.sosleitura.model.Usuario;
import com.livraria.sosleitura.repository.RoleRepository;
import com.livraria.sosleitura.security.UserDetailsCustom;
import com.livraria.sosleitura.service.JwtService;
import com.livraria.sosleitura.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@CrossOrigin
@RequestMapping(path = "/home")
@RestController
@Slf4j
public class HomeController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
    @Autowired
    private AuthenticationManager authenticationManager;
    @RequestMapping(method = RequestMethod.POST, path = "cadastro" ,params = {"login","password","nome"})
    public ResponseEntity<?> cadastroUsuario(@RequestParam(name = "login") String login,
                                             @RequestParam(name = "password") String password,
                                             @RequestParam(name = "nome") String nome) throws Exception {

        if(usuarioService.testeLoginExists(login)){
            throw new Exception("entidade existente!");
        }else{
            Usuario usuario = new Usuario();
            Role role = roleRepository.findByNome("USUARIO");
            usuario.setLogin(login);
            usuario.setSenha(encoder.encode(password));
            usuario.setNome(nome);
            usuario.setStatusUser(StatusUser.NOVO);
            usuario.getRoles().add(role);

            usuario = usuarioService.salvaUsuario(usuario);

            applicationEventPublisher.publishEvent(new UserCadastroEvent(this,usuario));

        }

        return ResponseEntity.ok().build();
    }
    @RequestMapping(method = RequestMethod.POST, path = "login", params = {"login","password"})
    public ResponseEntity<?> loginUsuario(@RequestParam(name = "login") String login,
                                          @RequestParam(name = "password") String password) throws Exception {

            Usuario usuario = usuarioService.buscaUsuarioByLogin(login);

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(new UserDetailsCustom(usuario), password));


            String token = jwtService.codeJwtToken(new HashMap<>(),usuario.getLogin());
            return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION,token).build();
    }
}
