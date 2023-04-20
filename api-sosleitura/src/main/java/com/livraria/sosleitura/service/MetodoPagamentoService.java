package com.livraria.sosleitura.service;


import com.livraria.sosleitura.model.MetodoPagamento;
import com.livraria.sosleitura.repository.MetodoPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetodoPagamentoService {

    @Autowired
    private MetodoPagamentoRepository repository;

    public MetodoPagamento salvaMetodoPagamento(MetodoPagamento novo){
        novo = repository.save(novo);
        return novo;
    }
    public MetodoPagamento buscaMetodoPagamentoById(Integer id){
        return repository.findById(id).orElseThrow();
    }
    public List<MetodoPagamento> buscaAllMetodoPagamento(){
        return repository.findAll();
    }
    public MetodoPagamento atualizaMetodoPagamento(MetodoPagamento novo, Integer id){
        MetodoPagamento metodoPagamento = buscaMetodoPagamentoById(id);
        metodoPagamento = novo;
        return repository.save(metodoPagamento);
    }
    public void deletaMetodoPagamento(Integer id){
        repository.deleteById(id);
    }
}
