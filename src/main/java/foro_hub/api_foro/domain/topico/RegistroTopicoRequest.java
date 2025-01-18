package foro_hub.api_foro.domain.topico;

import jakarta.validation.constraints.NotBlank;

public record RegistroTopicoRequest(
        @NotBlank
        String mensaje,
        @NotBlank
        String nombreCurso,
        @NotBlank
        String titulo
) {
}