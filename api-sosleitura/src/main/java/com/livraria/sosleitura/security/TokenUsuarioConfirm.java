package com.livraria.sosleitura.security;

import com.livraria.sosleitura.model.Usuario;
import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDateTime;

@Data
@Table(name = "token_usuario")
@Entity
public class TokenUsuarioConfirm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario")
    private Usuario usuario;
    private String token;
    private LocalDateTime data;

}
