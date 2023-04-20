package com.livraria.sosleitura.model;

public enum PedidoStatus {

    ABERTO("ABETO"),
    CONFIRMADO("CONFIRMADO"),
    COMPLETADO("COMPLETADO"),
    CANCELADO("CANCELADO");

    String status;

    public String getStatus() {
        return status;
    }

    PedidoStatus(String i){

    }
}
