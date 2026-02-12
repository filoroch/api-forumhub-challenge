package com.alura.api.forumhub.entity;

import jakarta.persistence.*;

@Table(name = "curso")
@Entity
public class Curso {
    @Id
    private Long id;
    private String nome;

    @Enumerated(value = EnumType.STRING)
    private Categoria categoria;

    public Long getId() {
        return id;
    }
}
