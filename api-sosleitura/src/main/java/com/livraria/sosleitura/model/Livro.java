package com.livraria.sosleitura.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;


@Data
@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @ManyToOne(targetEntity = Genero.class)
    @JoinColumn(name = "genero")
    private Genero genero;

    @ManyToOne(targetEntity = Autor.class)
    @JoinColumn(name = "autor")
    private Autor autor;

    private String resumo;

    @Column(columnDefinition = "char")
    private String isbn;

    private String capa;

    private LocalDate ano;

    private Double valor;

}
