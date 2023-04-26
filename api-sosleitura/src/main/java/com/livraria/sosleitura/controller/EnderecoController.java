package com.livraria.sosleitura.controller;

import com.livraria.sosleitura.model.Autor;
import com.livraria.sosleitura.model.Endereco;
import com.livraria.sosleitura.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/api/v1/endereco")
@RestController
public class EnderecoController {

    @Autowired
    private EnderecoService service;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> salvaEndereco(@RequestBody Endereco novo){
        Endereco endereco = service.salvaEndereco(novo);
        return ResponseEntity.status(201).body(endereco);
    }
    @RequestMapping(path = "/{id}")
    public ResponseEntity<?> findEndereco(@PathVariable(name = "id") Integer id){
        Endereco endereco = service.buscaEnderecoById(id);
        return ResponseEntity.ok(id);
    }
    @RequestMapping
    public ResponseEntity<?> findAllEndereco(){
        List<Endereco> enderecos = service.buscaAllEndereco();
        return ResponseEntity.ok(enderecos);
    }
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateAllEndereco(@PathVariable(name = "id") Integer id, @RequestBody Endereco novo){
        Endereco endereco = service.atualizaEndereco(novo,id);
        return ResponseEntity.ok(endereco);
    }
    @RequestMapping(path = "/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateEndereco(@PathVariable(name = "id") Integer id,
                                            @RequestBody Map<String,Object> campos){
        Endereco endereco = service.buscaEnderecoById(id);
        Endereco finalEndereco = endereco;
        campos.entrySet().forEach((entry)->{
            ReflectionUtils.makeAccessible(ReflectionUtils.findField(Autor.class,entry.getKey()));
            ReflectionUtils.setField(ReflectionUtils.findField(Autor.class,entry.getKey()), finalEndereco,entry.getValue());
        });
        endereco = service.atualizaEndereco(finalEndereco,id);
        return ResponseEntity.ok(endereco);
    }
    @RequestMapping(path = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<?> salvaEndereco(@PathVariable(name="id") Integer id){
        service.deletaEndereco(id);
        return ResponseEntity.noContent().build();
    }
}
