package com.livraria.sosleitura.service;


import com.livraria.sosleitura.model.Endereco;
import com.livraria.sosleitura.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository repository;

    public Endereco salvaEndereco(Endereco novo){
        novo = repository.save(novo);

        return novo;
    }
    public Endereco buscaEnderecoById(Integer id){
        return repository.findById(id).orElseThrow();
    }
    public List<Endereco> buscaAllEndereco(){
        return repository.findAll();
    }
    public Endereco atualizaEndereco(Endereco novo,Integer id){
        Endereco endereco = buscaEnderecoById(id);
        endereco = novo;
        endereco.setId(id);

        return repository.save(endereco);
    }
    public void deletaEndereco(Integer id){
        repository.deleteById(id);
    }
}
