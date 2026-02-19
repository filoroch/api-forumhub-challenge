package com.alura.api.forumhub.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record TopicoFiltroDTO(
        String nomeCurso,

        @Min(value = 1900, message = "Ano do curso não pode ser menor que 1900")
        @Max(value = 2100, message = "Ano do curso não pode ser maior que 2100")
        Integer anoCurso
) {
}
