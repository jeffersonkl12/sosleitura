package com.livraria.sosleitura.error;

public class CadastroException extends RuntimeException{
    private String message;
    public CadastroException(String message) {
        super(message);
        this.message = message;
    }

    public CadastroException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }
}
