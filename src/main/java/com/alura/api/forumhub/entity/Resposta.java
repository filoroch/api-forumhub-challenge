package com.alura.api.forumhub.entity;

import jakarta.persistence.*;
import com.alura.api.forumhub.entity.Usuario;
import java.time.LocalDate;

@Entity()
@Table(name = "resposta")
public class Resposta {
    @Id
    private Long id;
    private String mensagem;

    @ManyToOne
    @JoinColumn(name = "topico", referencedColumnName = "id")
    private Topico topico;
    private LocalDate data_criacao;

    @OneToOne()
    @JoinColumn(name = "autor")
    private Usuario autor;
    private String solucao;

    public Topico getTopico() {
        return topico;
    }

    public void setTopico(Topico topico) {
        this.topico = topico;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
