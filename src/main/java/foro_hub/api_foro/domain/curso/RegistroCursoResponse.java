package foro_hub.api_foro.domain.curso;

public record RegistroCursoResponse(
        Long id,
        String nombre,
        String categoria
) {
    public RegistroCursoResponse(Curso curso) {
        this(curso.getId(), curso.getNombre(), curso.getCategoria());
    }
}