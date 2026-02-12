package com.alura.api.forumhub.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

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
    private LocalDate data_criacao;

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

//    public Topico(Integer id, String titulo, String mensagem, LocalDate data_criacao, Status status, Usuario autor, Curso curso, Set<Resposta> respostas) {
//        this.id = id;
//        this.titulo = titulo;
//        this.mensagem = mensagem;
//        this.data_criacao = data_criacao;
//        this.status = status;
//        this.autor = autor;
//        this.curso = curso;
//        this.respostas = respostas;
//    }


    public Integer getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDate getData_criacao() {
        return data_criacao;
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
}
