package com.alura.api.forumhub.entity;

import jakarta.persistence.*;

import java.util.Set;

@Table(name = "usuario")
@Entity()
public class Usuario {
    @Id
    private Integer id;
    private String nome;
    private String email;
    private String senha;

    @OneToMany(mappedBy = "id")
    private Set<Perfil> perfis;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

}
