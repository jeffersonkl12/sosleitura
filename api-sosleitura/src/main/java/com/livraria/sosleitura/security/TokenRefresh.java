package com.livraria.sosleitura.security;

import com.livraria.sosleitura.model.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TokenRefresh {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "usuario")
    private Usuario usuario;
    private LocalDateTime expires;
    @Column(name = "creat_at")
    private LocalDateTime creatAt;
}
