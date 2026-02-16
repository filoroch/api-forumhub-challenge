package com.alura.api.forumhub.repository;

import com.alura.api.forumhub.entity.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ITopicoRepository extends JpaRepository<Topico, Integer>, JpaSpecificationExecutor<Topico> {
    boolean existsByTituloAndMensagem(String titulo, String mensagem);
}
