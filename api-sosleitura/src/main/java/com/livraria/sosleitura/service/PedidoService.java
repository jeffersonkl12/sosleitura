package com.livraria.sosleitura.service;


import com.livraria.sosleitura.model.Pedido;
import com.livraria.sosleitura.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    public Pedido salvaPedido(Pedido novo){
        novo = repository.save(novo);
        return novo;
    }
    public Pedido buscaPedidoById(Integer id){
        return repository.findById(id).orElseThrow();
    }
    public List<Pedido> buscaAllPedido(){
        return repository.findAll();
    }
    public Pedido atualizaPedido(Pedido novo,Integer id){
        if (!repository.existsById(id)){
            throw new NoSuchElementException("elemento nao encontrado");
        }
        novo.setId(id);
        return repository.save(novo);
    }
    public void deletaPedido(Integer id){
        repository.deleteById(id);
    }
}
