package com.livraria.sosleitura.controller;

import com.livraria.sosleitura.model.Autor;
import com.livraria.sosleitura.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RequestMapping(path = "/api/v1/autor")
@RestController
public class AutorController {

    @Autowired
    private AutorService service;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> salvaAutor(@RequestBody Autor novo){
        Autor autor = service.salvaAutor(novo);
        return ResponseEntity.status(201).body(autor);
    }
    @RequestMapping(path = "/{id}")
    public ResponseEntity<?> findAutor(@PathVariable(name = "id") Integer id){
        Autor autor = service.buscaAutor(id);
        return ResponseEntity.ok(autor);
    }
    @RequestMapping
    public ResponseEntity<?> findAllAutor(){
        List<Autor> autores = service.buscaAutorLista();
        return ResponseEntity.ok(autores);
    }
    @RequestMapping(path = "/{id}",method = RequestMethod.PUT)
    public ResponseEntity<?> updateAllAutor(@PathVariable(name = "id") Integer id,@RequestBody Autor novo){
        Autor autor = service.atualizaAutor(novo,id);
        return ResponseEntity.ok(autor);
    }
    @RequestMapping(path = "/{id}",method = RequestMethod.PATCH)
    public ResponseEntity<?> updateAutor(@PathVariable(name = "id") Integer id,@RequestBody Map<String,Object> campos) throws NoSuchFieldException, SecurityException{
        Autor autor = service.buscaAutor(id);
        Autor finalAutor = autor;
        campos.entrySet().forEach((entry)->{
            ReflectionUtils.makeAccessible(ReflectionUtils.findField(Autor.class,entry.getKey()));
            ReflectionUtils.setField(ReflectionUtils.findField(Autor.class,entry.getKey()), finalAutor,entry.getValue());
        });
        autor = service.atualizaAutor(finalAutor,id);
        return ResponseEntity.ok(autor);
    }
    @RequestMapping(path = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAutor(@PathVariable(name = "id") Integer id){
        service.deletaAutor(id);
        return ResponseEntity.ok().build();
    }
}
