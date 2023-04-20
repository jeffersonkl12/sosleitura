package com.livraria.sosleitura.repository;


import com.livraria.sosleitura.model.MetodoPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetodoPagamentoRepository extends JpaRepository<MetodoPagamento,Integer> {
}
