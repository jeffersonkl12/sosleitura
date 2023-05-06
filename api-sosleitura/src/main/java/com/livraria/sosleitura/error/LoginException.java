package com.livraria.sosleitura.error;

public class LoginException extends RuntimeException{
    private String message;

    public LoginException(String message) {
        super(message);
        this.message = message;
    }

    public LoginException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }
}
