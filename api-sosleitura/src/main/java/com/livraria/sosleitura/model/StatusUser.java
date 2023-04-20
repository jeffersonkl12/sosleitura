package com.livraria.sosleitura.model;

public enum StatusUser {

    NOVO("NOVO"),
    ATIVADO("ATIVADO"),
    BLOQUEADO("BLOQUEADO"),
    BANIDO("BANIDO");

    String status;

    public String getStatus() {
        return status;
    }

    StatusUser(String i){
        this.status = i;
    }
}
