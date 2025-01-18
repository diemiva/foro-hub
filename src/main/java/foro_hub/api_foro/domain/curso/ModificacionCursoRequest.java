package foro_hub.api_foro.domain.curso;

import jakarta.validation.constraints.NotBlank;

public record ModificacionCursoRequest(
        @NotBlank
        String nombre,
        @NotBlank
        String categoria
) {
}