package com.livraria.sosleitura.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(property = "uuid",generator = ObjectIdGenerators.UUIDGenerator.class)
@Data
@Entity
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "genero")
    @ToString.Exclude
    private List<Livro> items = new ArrayList<>();

}
