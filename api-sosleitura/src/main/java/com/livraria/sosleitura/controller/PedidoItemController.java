package com.livraria.sosleitura.controller;

import com.livraria.sosleitura.model.Autor;
import com.livraria.sosleitura.model.PedidoItem;
import com.livraria.sosleitura.service.PedidoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/api/v1/pedido-item")
@RestController
public class PedidoItemController {

    @Autowired
    private PedidoItemService service;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> salvaPedidoItem(@RequestBody PedidoItem novo){
        PedidoItem pedidoItem = service.salvaPedidoItem(novo);
        return ResponseEntity.status(201).body(pedidoItem);
    }
    @RequestMapping(path = "/{id}")
    public ResponseEntity<?> findPedidoItem(@PathVariable(name = "id") Integer id){
        PedidoItem pedidoItem = service.buscaPedidoItemById(id);
        return ResponseEntity.ok().body(pedidoItem);
    }
    @RequestMapping
    public ResponseEntity<?> findAllPedidoItem(){
        List<PedidoItem> pedidoItems = service.buscaAllPedidoItem();
        return ResponseEntity.ok().body(pedidoItems);
    }
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateALlPedidoItem(@PathVariable(name = "id") Integer id,
                                                 @RequestBody PedidoItem novo){
        PedidoItem pedidoItem = service.atualizaPedidoItem(novo,id);
        return ResponseEntity.ok().body(pedidoItem);
    }
    @RequestMapping(path = "/{id}", method = RequestMethod.PATCH)
        public ResponseEntity<?> updatePedidoItem(@PathVariable(name = "id") Integer id,
                                            @RequestBody Map<String,Object> campos){
        PedidoItem pedidoItem = service.buscaPedidoItemById(id);
        PedidoItem finalPedidoItem = pedidoItem;
        campos.entrySet().forEach((entry)->{
            ReflectionUtils.makeAccessible(ReflectionUtils.findField(Autor.class,entry.getKey()));
            ReflectionUtils.setField(ReflectionUtils.findField(Autor.class,entry.getKey()), finalPedidoItem,entry.getValue());
        });
        pedidoItem = service.atualizaPedidoItem(finalPedidoItem,id);
        return ResponseEntity.ok(pedidoItem);
    }
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePedidoItem(@PathVariable(name = "id") Integer id){
        service.deletaPedidoItem(id);
        return ResponseEntity.noContent().build();
    }
}
