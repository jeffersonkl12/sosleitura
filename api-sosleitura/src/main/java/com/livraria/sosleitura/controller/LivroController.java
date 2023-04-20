package com.livraria.sosleitura.controller;

import com.livraria.sosleitura.model.Autor;
import com.livraria.sosleitura.model.Livro;
import com.livraria.sosleitura.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/api/v1/livro")
@RestController
public class LivroController {

    @Autowired
    private LivroService service;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> salvaLivro(@RequestBody Livro novo){
        Livro livro = service.salvaLivro(novo);
        return ResponseEntity.status(201).body(livro);
    }
    @RequestMapping(path = "/{id}")
    public ResponseEntity<?> findLivro(@PathVariable(name = "id") Integer id){
        Livro livro = service.buscaLivroById(id);
        return ResponseEntity.ok().body(livro);
    }
    @RequestMapping
    public ResponseEntity<?> findAllLivro(){
        List<Livro> livros = service.buscaAllLivro();
        return ResponseEntity.ok().body(livros);
    }
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateALlLivro(@PathVariable(name = "id") Integer id, @RequestBody Livro novo){
        Livro livro = service.atualizaLivro(novo,id);
        return ResponseEntity.ok().body(livro);
    }
    @RequestMapping(path = "/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateEndereco(@PathVariable(name = "id") Integer id,
                                            @RequestBody Map<String,Object> campos){
        Livro livro = service.buscaLivroById(id);
        Livro finalLivro = livro;
        campos.entrySet().forEach((entry)->{
            ReflectionUtils.makeAccessible(ReflectionUtils.findField(Autor.class,entry.getKey()));
            ReflectionUtils.setField(ReflectionUtils.findField(Autor.class,entry.getKey()), finalLivro,entry.getValue());
        });
        livro = service.atualizaLivro(finalLivro,id);
        return ResponseEntity.ok(livro);
    }
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteLivro(@PathVariable(name = "id") Integer id){
        service.deletaLivro(id);
        return ResponseEntity.ok().build();
    }

}
