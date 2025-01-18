package foro_hub.api_foro.controller;

import foro_hub.api_foro.domain.usuarios.DatosAutenticacionUsuario;
import foro_hub.api_foro.domain.usuarios.RegistroUsuarioRequest;
import foro_hub.api_foro.domain.usuarios.RegistroUsuarioResponse;
import foro_hub.api_foro.domain.usuarios.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<RegistroUsuarioResponse> crearUsuario(@RequestBody @Valid RegistroUsuarioRequest registroUsuarioRequest) {
        RegistroUsuarioResponse response = usuarioService.crearUsuario(registroUsuarioRequest);
        return ResponseEntity.status(201).body(response);
    }
}