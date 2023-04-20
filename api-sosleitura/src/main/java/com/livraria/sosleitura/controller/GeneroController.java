package com.livraria.sosleitura.controller;

import com.livraria.sosleitura.model.Autor;
import com.livraria.sosleitura.model.Genero;
import com.livraria.sosleitura.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/api/v1/genero")
@RestController
public class GeneroController {

    @Autowired
    private GeneroService service;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> salvaGenero(@RequestBody Genero novo){
        Genero genero = service.salvaGenero(novo);
        return ResponseEntity.status(201).body(genero);
    }
    @RequestMapping(path = "/{id}")
    public ResponseEntity<?> findGenero(@PathVariable(name="id") Integer id){
        Genero genero = service.buscaGeneroById(id);
        return ResponseEntity.ok().body(genero);
    }
    @RequestMapping
    public ResponseEntity<?> findAllGenero(){
        List<Genero> generos = service.buscaAllgenero();
        return ResponseEntity.ok().body(generos);
    }
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateAllGenero(@PathVariable(name = "id") Integer id, @RequestBody Genero novo){
        Genero genero = service.atualizaGenero(novo,id);
        return ResponseEntity.ok().body(genero);
    }
    @RequestMapping(path = "/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateEndereco(@PathVariable(name = "id") Integer id,
                                            @RequestBody Map<String,Object> campos){
        Genero genero = service.buscaGeneroById(id);
        Genero finalEndereco = genero;
        campos.entrySet().forEach((entry)->{
            ReflectionUtils.makeAccessible(ReflectionUtils.findField(Autor.class,entry.getKey()));
            ReflectionUtils.setField(ReflectionUtils.findField(Autor.class,entry.getKey()), finalEndereco,entry.getValue());
        });
        genero = service.atualizaGenero(finalEndereco,id);
        return ResponseEntity.ok(genero);
    }
    @RequestMapping(path = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteGenero(@PathVariable(name = "id") Integer id, @RequestBody Genero novo){
        service.deletaGenero(id);
        return ResponseEntity.ok().build();
    }

}
