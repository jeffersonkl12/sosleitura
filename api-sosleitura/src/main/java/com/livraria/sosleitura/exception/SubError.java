package com.livraria.sosleitura.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubError extends ApiSubError{
    private String object;
    private String field;
    private String message;
    private Object valueRejected;

    public SubError(String object, String message) {
        this.object = object;
        this.message = message;
    }
}
