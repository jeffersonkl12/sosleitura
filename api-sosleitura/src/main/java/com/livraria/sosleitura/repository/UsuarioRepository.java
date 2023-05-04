package com.livraria.sosleitura.repository;


import com.livraria.sosleitura.model.Usuario;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
    @EntityGraph(attributePaths = {"roles"})
    public Optional<Usuario> findByLogin(String login);
    public Boolean existsByLogin(String login);
    public Optional<Usuario> findBySenha(String password);
}
