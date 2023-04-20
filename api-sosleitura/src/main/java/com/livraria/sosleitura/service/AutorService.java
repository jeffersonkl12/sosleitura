package com.livraria.sosleitura.service;


import com.livraria.sosleitura.model.Autor;
import com.livraria.sosleitura.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class AutorService {

    @Autowired
    private AutorRepository repository;

    public Autor salvaAutor(Autor novo){
        return repository.save(novo);
    }
    public List<Autor> buscaAutorLista(){
        return repository.findAll();
    }
    public Autor buscaAutor(Integer id){
        return repository.findById(id).orElseThrow();
    }
    public Autor atualizaAutor(Autor novo, Integer id){
        Autor aux = buscaAutor(id);
        aux = novo;
        aux.setId(id);
        return repository.save(aux);
    }
    public void deletaAutor(Integer id){
        repository.deleteById(id);
    }
}
