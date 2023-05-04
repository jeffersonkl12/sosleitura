package com.livraria.sosleitura.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String login;

    private String senha;

    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_user",columnDefinition = "ENUM(\"NOVO\",\"ATIVADO\",\"BLOQUEADO\",\"BANIDO\") NOT NULL DEFAULT \"NOVO\"")
    private StatusUser statusUser;

    @ManyToOne
    @JoinColumn(name = "endereco")
    private Endereco endereco;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
    joinColumns = @JoinColumn(name = "usuario"),
    inverseJoinColumns = @JoinColumn(name = "role"))
    private List<Role> roles = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "usuario")
    @ToString.Exclude
    private List<Pedido> pedidos;
}
