package com.livraria.sosleitura.model;

public enum PagamentoStatus {

    NAO_PAGO("NAO PAGO"),
    FALHOU("FALHOU"),
    EXPIRADO("EXPIRADO"),
    PAGO("PAGO"),
    REEMBOLSO("REEMBOLSADO");

    String status;

    public String getStatus() {
        return status;
    }

    PagamentoStatus(String i){

    }
}
