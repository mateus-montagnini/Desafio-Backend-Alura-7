package com.mrocha.desafio.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_depoimentos")
@Data
public class Depoimento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "foto")
    private String foto;

    @Column(name = "depoimento")
    private String depoimento;

    @Column(name = "nome")
    private String nome;
}
