package com.livraria.sosleitura.error;

public class TokenRefreshNotValidException extends RuntimeException{
    private String message;

    public TokenRefreshNotValidException(){
        super();
    }
    public TokenRefreshNotValidException(String message){
        super(message);
    }
    public TokenRefreshNotValidException(String message,Throwable cause){
        super(message,cause);
    }
}
