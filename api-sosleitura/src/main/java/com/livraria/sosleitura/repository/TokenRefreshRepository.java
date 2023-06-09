package com.livraria.sosleitura.repository;

import com.livraria.sosleitura.model.Usuario;
import com.livraria.sosleitura.security.TokenRefresh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TokenRefreshRepository extends CrudRepository<TokenRefresh, UUID> {
    public void deleteByUsuario(Usuario usuario);
    public Boolean existsByUsuario(Usuario usuario);
}
