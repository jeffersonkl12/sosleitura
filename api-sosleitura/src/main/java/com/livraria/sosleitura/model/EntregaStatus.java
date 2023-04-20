package com.livraria.sosleitura.model;

public enum EntregaStatus {

    NAO_COMPRIDO("NAO COMPRIDO"),
    ENVIO("ENVIO"),
    ENVIADO("ENVIADO"),
    CHEGOU("CHEGOU"),
    COLETADO("COLETADO"),
    DEVOLVIDO("DEVOLVIDO");

    String status;

    public String getStatus() {
        return status;
    }

    EntregaStatus(String i) {
    }
}
