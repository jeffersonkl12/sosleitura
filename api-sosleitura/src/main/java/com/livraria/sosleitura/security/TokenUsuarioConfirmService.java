package com.livraria.sosleitura.security;

import com.livraria.sosleitura.repository.TokenUsuarioConfirmRepository;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Service
public class TokenUsuarioConfirmService {

    private static Logger logger = LoggerFactory.getLogger(TokenUsuarioConfirm.class);
    @Autowired
    private TokenUsuarioConfirmRepository repository;

    public TokenUsuarioConfirm findByToken(String token){
        return repository.findByToken(token);
    }

    public TokenUsuarioConfirm salvaToken(TokenUsuarioConfirm token) throws Exception {
        if(token.getData().isAfter(LocalDateTime.now())){
            throw new Exception("datainvalida!");
        }else{
            return repository.save(token);
        }
    }

    public Boolean validaToken(String token){
        if(repository.existsByToken(token)){
            TokenUsuarioConfirm tokenUser = repository.findByToken(token);
            if(tokenUser.getData().plusDays(1).isAfter(LocalDateTime.now())){
                return true;
            }
        }
        return false;
    }
}
