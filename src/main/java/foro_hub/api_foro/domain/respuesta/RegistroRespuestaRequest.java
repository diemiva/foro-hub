package foro_hub.api_foro.domain.respuesta;

import jakarta.validation.constraints.NotBlank;

public record RegistroRespuestaRequest(
        @NotBlank
        String mensaje
) {
}