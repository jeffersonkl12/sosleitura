package com.livraria.sosleitura.service;



import com.livraria.sosleitura.model.Usuario;
import com.livraria.sosleitura.repository.RoleRepository;
import com.livraria.sosleitura.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    public Usuario salvaUsuario(Usuario novo){
        return repository.save(novo);
    }
    public Usuario buscaUsuarioById(Integer id){
        return repository.findById(id).orElseThrow();
    }
    public List<Usuario> buscaAllUsuario(){
        return repository.findAll();
    }
    public Usuario atualizaUsuario(Usuario novo,Integer id){
        Usuario aux = buscaUsuarioById(id);
        aux = novo;
        aux.setId(id);
        return repository.save(aux);
    }
    public void deletaUsuario(Integer id){
        repository.deleteById(id);
    }

    public Boolean testeLoginExists(String login){
        return repository.existsByLogin(login);
    }

    public Usuario buscaUsuarioByLogin(String login){
        return repository.findByLogin(login).orElseThrow(()->{throw new NoSuchElementException("usuario nao encontrado");});
    }
    public Usuario buscaUsuarioByPassword(String password){
        return repository.findBySenha(password).orElseThrow(()->{throw new NoSuchElementException("senha invalida");});
    }
}
