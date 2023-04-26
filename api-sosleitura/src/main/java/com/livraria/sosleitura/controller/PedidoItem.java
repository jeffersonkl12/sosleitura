package com.livraria.sosleitura.controller;

import com.livraria.sosleitura.model.Autor;
import com.livraria.sosleitura.model.Pedido;
import com.livraria.sosleitura.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/api/v1/pedido")
@RestController
public class PedidoItem {

    @Autowired
    private PedidoService service;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> salvaPedido(@RequestBody Pedido novo){
        Pedido pedido = service.salvaPedido(novo);
        return ResponseEntity.status(201).body(pedido);
    }
    @RequestMapping(path = "/{id}")
    public ResponseEntity<?> findPedido(@PathVariable(name = "id") Integer id){
        Pedido pedido = service.buscaPedidoById(id);
        return ResponseEntity.ok().body(pedido);
    }
    @RequestMapping
    public ResponseEntity<?> findAllPedido(){
        List<Pedido> pedidos = service.buscaAllPedido();
        return ResponseEntity.ok().body(pedidos);
    }
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateALlPedido(@PathVariable(name = "id") Integer id,
                                                 @RequestBody Pedido novo){
        Pedido pedido = service.atualizaPedido(novo,id);
        return ResponseEntity.ok().body(pedido);
    }
    @RequestMapping(path = "/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<?> updatePedido(@PathVariable(name = "id") Integer id,
                                              @RequestBody Map<String,Object> campos){
        Pedido pedido = service.buscaPedidoById(id);
        Pedido finalPedido = pedido;
        campos.entrySet().forEach((entry)->{
            ReflectionUtils.makeAccessible(ReflectionUtils.findField(Autor.class,entry.getKey()));
            ReflectionUtils.setField(ReflectionUtils.findField(Autor.class,entry.getKey()), finalPedido,entry.getValue());
        });
        pedido = service.atualizaPedido(finalPedido,id);
        return ResponseEntity.ok(pedido);
    }
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePedidoItem(@PathVariable(name = "id") Integer id){
        service.deletaPedido(id);
        return ResponseEntity.noContent().build();
    }
}
