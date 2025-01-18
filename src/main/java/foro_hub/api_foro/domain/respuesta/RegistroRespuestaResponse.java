package foro_hub.api_foro.domain.respuesta;

public record RegistroRespuestaResponse(
        Long id,
        String mensaje,
        String tituloTopico
) {
    public RegistroRespuestaResponse(Respuesta respuesta) {
        this(respuesta.getId(), respuesta.getMensaje(), respuesta.getTopico().getTitulo());
    }
}