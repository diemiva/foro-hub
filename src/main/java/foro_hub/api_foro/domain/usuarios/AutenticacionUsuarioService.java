package foro_hub.api_foro.domain.usuarios;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AutenticacionUsuarioService {

    public Usuario obtenerUsuarioAutenticado() {
        return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}