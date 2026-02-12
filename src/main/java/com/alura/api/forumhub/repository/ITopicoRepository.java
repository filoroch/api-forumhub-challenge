package com.alura.api.forumhub.repository;

import com.alura.api.forumhub.entity.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITopicoRepository extends JpaRepository<Topico, Integer> {
    boolean existsByTituloAndMensagem(String titulo, String mensagem);
}
