package com.livraria.sosleitura.exception;

import com.livraria.sosleitura.error.CadastroException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.security.auth.login.LoginException;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@RestControllerAdvice
@Slf4j
public class HandlerException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {NoSuchElementException.class})
    public ResponseEntity<?> handlerNoSuchElementException(NoSuchElementException ex, WebRequest request){
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND,"entidade nao encontrada",LocalDateTime.now(),ex.getLocalizedMessage(),null);
        return super.handleExceptionInternal(ex,apiError,new HttpHeaders(),HttpStatus.NOT_FOUND,request);
    }
    @ExceptionHandler(value = {LoginException.class})
    public ResponseEntity<?> handlerLoginException(LoginException ex, WebRequest request){
        ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED,"login invalido",LocalDateTime.now(),ex.getLocalizedMessage(),null);
        log.info(apiError.toString());
        return super.handleExceptionInternal(ex,apiError,new HttpHeaders(),HttpStatus.UNAUTHORIZED,request);
    }
    @ExceptionHandler(value = {CadastroException.class})
    public ResponseEntity<?> handlerCadastroException(CadastroException ex, WebRequest request){
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST,"cadastro invalido",LocalDateTime.now(),ex.getLocalizedMessage(),null);
        return super.handleExceptionInternal(ex,apiError,new HttpHeaders(),HttpStatus.BAD_REQUEST,request);
    }
}
