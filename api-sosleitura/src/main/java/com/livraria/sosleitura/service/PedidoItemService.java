package com.livraria.sosleitura.service;


import com.livraria.sosleitura.model.PedidoItem;
import com.livraria.sosleitura.repository.PedidoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PedidoItemService {

    @Autowired
    private PedidoItemRepository repository;

    public PedidoItem salvaPedidoItem(PedidoItem novo){
        return repository.save(novo);
    }
    public PedidoItem buscaPedidoItemById(Integer id){
        return repository.findById(id).orElseThrow();
    }
    public List<PedidoItem> buscaAllPedidoItem(){
        return repository.findAll();
    }
    public PedidoItem atualizaPedidoItem(PedidoItem novo,Integer id){
        if(!repository.existsById(id)){
            throw new NoSuchElementException("elemente nao encontrado");
        }
        novo.setId(id);
        return repository.save(novo);
    }
    public void deletaPedidoItem(Integer id){
        repository.deleteById(id);
    }
}
