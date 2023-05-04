package com.livraria.sosleitura.repository;

import com.livraria.sosleitura.security.TokenUsuarioConfirm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenUsuarioConfirmRepository  extends JpaRepository<TokenUsuarioConfirm,Integer> {
    public Boolean existsByToken(String token);
    public TokenUsuarioConfirm findByToken(String token);
}
