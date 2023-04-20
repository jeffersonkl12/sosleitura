package com.livraria.sosleitura.repository;

import com.livraria.sosleitura.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco,Integer> {
}
