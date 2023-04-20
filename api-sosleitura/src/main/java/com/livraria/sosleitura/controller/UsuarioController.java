package com.livraria.sosleitura.controller;


import com.livraria.sosleitura.model.Autor;
import com.livraria.sosleitura.model.Usuario;
import com.livraria.sosleitura.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/api/v1/usuario")
@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> salvaUsuario(@RequestBody Usuario novo){
        Usuario usuario = service.salvaUsuario(novo);
        return ResponseEntity.status(201).body(usuario);
    }
    @RequestMapping(path = "/{id}")
    public ResponseEntity<?> findUsuario(@PathVariable(name = "id") Integer id){
        Usuario usuario = service.buscaUsuarioById(id);
        return ResponseEntity.ok(usuario);
    }
    @RequestMapping
    public ResponseEntity<?> findAllUsuario(){
        List<Usuario> usuarios = service.buscaAllUsuario();
        return ResponseEntity.ok(usuarios);
    }
    @RequestMapping(path = "/{id}",method = RequestMethod.PUT)
    public ResponseEntity<?> updateAllUsuario(@PathVariable(name = "id") Integer id,@RequestBody Usuario novo){
        Usuario usuario = service.atualizaUsuario(novo,id);
        return ResponseEntity.ok(usuario);
    }
    @RequestMapping(path = "/{id}",method = RequestMethod.PATCH)
    public ResponseEntity<?> updateUsuario(@PathVariable(name = "id") Integer id,
                                           @RequestBody Map<String,Object> campos) throws NoSuchFieldException, SecurityException{
        Usuario usuario = service.buscaUsuarioById(id);
        Usuario finalUsuario = usuario;
        campos.entrySet().forEach((entry)->{
            ReflectionUtils.makeAccessible(ReflectionUtils.findField(Autor.class,entry.getKey()));
            ReflectionUtils.setField(ReflectionUtils.findField(Autor.class,entry.getKey()), finalUsuario,entry.getValue());
        });
        usuario = service.atualizaUsuario(finalUsuario,id);
        return ResponseEntity.ok(usuario);
    }
    @RequestMapping(path = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUsuario(@PathVariable(name = "id") Integer id){
        service.deletaUsuario(id);
        return ResponseEntity.ok().build();
    }
}
