package com.livraria.sosleitura.event;

import com.livraria.sosleitura.model.Usuario;
import org.springframework.context.ApplicationEvent;

public class UserCadastroEvent extends ApplicationEvent {

    private Usuario usuario;
    public UserCadastroEvent(Object source, Usuario usuario) {
        super(source);
        this.usuario = usuario;
    }

    public Usuario getUsuario(){
        return this.usuario;
    }
}
