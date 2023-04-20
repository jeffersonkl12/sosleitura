package com.livraria.sosleitura.service;


import com.livraria.sosleitura.model.Livro;
import com.livraria.sosleitura.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;

    public Livro salvaLivro(Livro novo){
        return repository.save(novo);
    }
    public Livro buscaLivroById(Integer id){
        return repository.findById(id).orElseThrow();
    }
    public List<Livro> buscaAllLivro(){
        return repository.findAll();
    }
    public Livro atualizaLivro(Livro novo, Integer id){
        Livro aux = buscaLivroById(id);
        aux = novo;
        aux.setId(id);
        return repository.save(aux);
    }
    public void deletaLivro(Integer id){
        repository.deleteById(id);
    }
}
