package com.livraria.sosleitura.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "metodo_pagamento")
@Entity
public class MetodoPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String metodo;
}
