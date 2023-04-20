package com.livraria.sosleitura.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@JsonIdentityInfo(property = "uuid",generator = ObjectIdGenerators.UUIDGenerator.class)
@Data
@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "data_pedido")
    private LocalDate dataPedido;

    @Enumerated(EnumType.STRING)
    @Column(name = "pedido_status", columnDefinition = "ENUM(\"ABERTO\",\"CONFIRMADO\",\"COMPLETADO\",\"CANCELADO\") NOT NULL DEFAULT \"ABERTO\"")
    private PedidoStatus pedidoStatus;


    @Enumerated(EnumType.STRING)
    @Column(name = "pagamento_status", columnDefinition = "ENUM(\"NAO PAGO\",\"FALHOU\",\"EXPIRADO\",\"PAGO\",\"REEMBOLSADO\") NOT NULL DEFAULT \"NAO PAGO\"")
    private PagamentoStatus pagamentoStatus;


    @Enumerated(EnumType.STRING)
    @Column(name = "entrega_status", columnDefinition = "ENUM(\"NAO COMPRIDO\", \"ENVIO\",\"ENVIADO\",\"CHEGOU\",\"COLETADO\",\"DEVOLVIDO\") NOT NULL DEFAULT \"NAO COMPRIDO\"")
    private EntregaStatus entregaStatus;

    @ManyToOne
    @JoinColumn(name = "usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "metodo_pagamento")
    private MetodoPagamento metodoPagamento;

    @ManyToOne
    @JoinColumn(name = "endereco_destino")
    private Endereco enderecoDestino;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "pedido",cascade = CascadeType.PERSIST)
    @ToString.Exclude
    private List<PedidoItem> pedidoItem;
}
