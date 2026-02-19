package com.alura.api.forumhub.dto;

public record AtualizarTopicoDTO(
        String titulo,
        String mensagem,
        Integer autor_id,
        Integer curso_id
) {
}
