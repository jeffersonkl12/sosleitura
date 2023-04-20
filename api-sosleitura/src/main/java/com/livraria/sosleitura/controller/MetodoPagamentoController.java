package com.livraria.sosleitura.controller;

import com.livraria.sosleitura.model.Autor;
import com.livraria.sosleitura.model.MetodoPagamento;
import com.livraria.sosleitura.service.MetodoPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/api/v1/metodo-pagamento")
@RestController
public class MetodoPagamentoController {

    @Autowired
    private MetodoPagamentoService service;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> salvaMetodoPagamento(@RequestBody MetodoPagamento novo){
        MetodoPagamento metodoPagamento = service.salvaMetodoPagamento(novo);
        return ResponseEntity.status(201).body(metodoPagamento);
    }
    @RequestMapping(path = "/{id}")
    public ResponseEntity<?> findMetodoPagamento(@PathVariable(name = "id") Integer id){
        MetodoPagamento metodoPagamento = service.buscaMetodoPagamentoById(id);
        return ResponseEntity.ok().body(metodoPagamento);
    }
    @RequestMapping
    public ResponseEntity<?> findAllMetodoPagamento(){
        List<MetodoPagamento> metodoPagamentos = service.buscaAllMetodoPagamento();
        return ResponseEntity.ok().body(metodoPagamentos);
    }
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateALlMetodoPagamento(@PathVariable(name = "id") Integer id,
                                                      @RequestBody MetodoPagamento novo){
        MetodoPagamento metodoPagamento = service.atualizaMetodoPagamento(novo,id);
        return ResponseEntity.ok().body(metodoPagamento);
    }
    @RequestMapping(path = "/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateMetodoPagamento(@PathVariable(name = "id") Integer id,
                                            @RequestBody Map<String,Object> campos){
        MetodoPagamento metodoPagamento = service.buscaMetodoPagamentoById(id);
        MetodoPagamento finalMetodoPagamento = metodoPagamento;
        campos.entrySet().forEach((entry)->{
            ReflectionUtils.makeAccessible(ReflectionUtils.findField(Autor.class,entry.getKey()));
            ReflectionUtils.setField(ReflectionUtils.findField(Autor.class,entry.getKey()),
                    finalMetodoPagamento,
                    entry.getValue());
        });
        metodoPagamento = service.atualizaMetodoPagamento(finalMetodoPagamento,id);
        return ResponseEntity.ok(metodoPagamento);
    }
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteMetodoPagamento(@PathVariable(name = "id") Integer id){
        service.deletaMetodoPagamento(id);
        return ResponseEntity.ok().build();
    }
}
