package com.livraria.sosleitura.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "pedido_item")
@Entity
public class PedidoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "item")
    private Livro item;

    private Integer qtd;

    @ManyToOne
    @JoinColumn(name = "pedido")
    private Pedido pedido;
}
