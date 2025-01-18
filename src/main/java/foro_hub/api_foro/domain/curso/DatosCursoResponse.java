package foro_hub.api_foro.domain.curso;

public record DatosCursoResponse(
        Long id,
        String nombre,
        String categoria
) {
    public DatosCursoResponse(Curso curso) {
        this(curso.getId(), curso.getNombre(), curso.getCategoria());
    }
}