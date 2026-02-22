package com.alura.api.forumhub.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record TopicoFiltroDTO(
        @Schema(description = "Nome do curso a quem o topico esta relacionado", example = "Erro ao configurar Docker")
        String nomeCurso,

        @Min(value = 1900, message = "Ano do curso não pode ser menor que 1900")
        @Max(value = 2100, message = "Ano do curso não pode ser maior que 2100")
        @Schema(description = "Ano do curso (inteiro)", example = "2026")
        Integer anoCurso
) {
}
