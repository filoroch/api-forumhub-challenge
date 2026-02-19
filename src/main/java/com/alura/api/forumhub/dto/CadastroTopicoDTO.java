package com.alura.api.forumhub.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CadastroTopicoDTO(
        @NotBlank(message = "Titulo é obrigatorio")
        @Size(min = 4, max = 60)
        @Schema(description = "Titulo do Topico", example = "Docker não funciona direito")
        String titulo,

        @NotBlank(message = "Mensagem é obrigatorio")
        @Size(min = 4, max = 60)
        @Schema(description = "Mensage,", example = "Pessoal, o container não inicia quando passo docker-Compose up -d")
        String mensagem,

        @NotNull(message = "ID do autor é obrigatorio")
        @Schema(description = "ID do autor. o ID precisa ser valido", example = "108")
        Integer autor_id,

        @NotNull(message = "ID do curso é obrigatorio")
        @Schema(description = "ID do curso. o ID precisa ser valido", example = "108")
        Integer curso_id
) {
}
