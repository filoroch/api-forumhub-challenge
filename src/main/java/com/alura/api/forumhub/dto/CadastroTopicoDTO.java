package com.alura.api.forumhub.dto;

import com.alura.api.forumhub.entity.Curso;
import com.alura.api.forumhub.entity.Usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CadastroTopicoDTO(
        @NotBlank(message = "Titulo é obrigatorio")
        @Size(min = 4, max = 60)
        String titulo,

        @NotBlank(message = "Mensagem é obrigatorio")
        @Size(min = 4, max = 60)
        String mensagem,

        @NotNull(message = "ID do autor é obrigatorio")
        Integer autor_id,

        @NotNull(message = "ID do curso é obrigatorio")
        Integer curso_id
) {
}
