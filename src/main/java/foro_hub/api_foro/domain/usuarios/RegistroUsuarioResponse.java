package foro_hub.api_foro.domain.usuarios;

public record RegistroUsuarioResponse(
        Long id,
        String nombre,
        String email
) {
    public RegistroUsuarioResponse(Usuario usuario) {
        this(usuario.getId(), usuario.getNombre(), usuario.getEmail());
    }
}