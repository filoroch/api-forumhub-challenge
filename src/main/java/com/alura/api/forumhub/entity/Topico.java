package com.alura.api.forumhub.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.Set;

@Table(name = "topico")
@Entity()
@NoArgsConstructor
@AllArgsConstructor
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titulo;
    private String mensagem;

    @Column(name = "data_criacao")
    private LocalDate dataCriacao;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    @OneToOne()
    @JoinColumn(name = "autor")
    private Usuario autor;

    @OneToOne()
    @JoinColumn(name = "curso")
    private Curso curso;

    @OneToMany(mappedBy = "topico")
    private Set<Resposta> respostas;

    public Integer getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public Status getStatus() {
        return status;
    }

    public Usuario getAutor() {
        return autor;
    }

    public Curso getCurso() {
        return curso;
    }

    public Set<Resposta> getRespostas() {
        return respostas;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
