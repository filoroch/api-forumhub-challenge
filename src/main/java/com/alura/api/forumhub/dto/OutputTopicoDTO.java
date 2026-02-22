package com.alura.api.forumhub.dto;

import com.alura.api.forumhub.entity.Curso;
import com.alura.api.forumhub.entity.Status;
import com.alura.api.forumhub.entity.Usuario;

public record OutputTopicoDTO(
        String titulo,
        String mensagem,
        Status status,
        Usuario autor,
        Curso curso
) {
}
