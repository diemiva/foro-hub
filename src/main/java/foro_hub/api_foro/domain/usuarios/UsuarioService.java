package foro_hub.api_foro.domain.usuarios;

import foro_hub.api_foro.domain.ValidacionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public RegistroUsuarioResponse crearUsuario(RegistroUsuarioRequest registroUsuarioRequest) {
        // Verificar si el usuario ya existe
        if (usuarioRepository.findByEmail(registroUsuarioRequest.email()) != null) {
            throw new ValidacionException("El email ya está en uso");
        }

        // Crear un nuevo usuario
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(registroUsuarioRequest.nombre());
        nuevoUsuario.setEmail(registroUsuarioRequest.email());
        nuevoUsuario.setClave(passwordEncoder.encode(registroUsuarioRequest.clave())); // Cifrar la contraseña

        // Guardar el usuario en la base de datos
        usuarioRepository.save(nuevoUsuario);

        return new RegistroUsuarioResponse(nuevoUsuario);
    }
}