package com.livraria.sosleitura.security;

import com.livraria.sosleitura.model.Usuario;
import com.livraria.sosleitura.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Slf4j
public class UserDetailServiceCustom implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        Usuario usuario = repository.findByLogin(login).orElseThrow(()-> {
            throw new UsernameNotFoundException("usuario nao encontrado");
        });
        UserDetailsCustom userDetails = new UserDetailsCustom(usuario);
        return userDetails;
    }
}
