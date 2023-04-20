package com.livraria.sosleitura.service;


import com.livraria.sosleitura.model.Genero;
import com.livraria.sosleitura.repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneroService {

    @Autowired
    private GeneroRepository repository;

    public Genero salvaGenero(Genero novo){
        return repository.save(novo);
    }
    public Genero buscaGeneroById(Integer id){
        return repository.findById(id).orElseThrow();
    }
    public List<Genero> buscaAllgenero(){
        return repository.findAll();
    }
    public Genero atualizaGenero(Genero novo, Integer id){
        Genero aux = buscaGeneroById(id);
        aux = novo;
        aux.setId(id);
        return repository.save(aux);
    }
    public void deletaGenero(Integer id){
        repository.deleteById(id);
    }
}
