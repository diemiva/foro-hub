package foro_hub.api_foro.domain.topico;


import java.time.LocalDateTime;

public record DetalleTopicoResponse(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        LocalDateTime fechaModificacion,
        Estado estado,
        String nombreAutor,
        String nombreCurso
) {
    public DetalleTopicoResponse(Topico topico){
        this(topico.getId(),topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getFechaModificacion(), topico.getEstado(), topico.getAutor().getNombre(), topico.getCurso().getNombre());
    }
}