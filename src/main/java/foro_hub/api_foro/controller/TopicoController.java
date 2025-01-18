package foro_hub.api_foro.controller;

import foro_hub.api_foro.domain.topico.RegistroTopicoRequest;
import foro_hub.api_foro.domain.topico.RegistroTopicoResponse;
import foro_hub.api_foro.domain.topico.TopicoService;
import foro_hub.api_foro.domain.usuarios.AutenticacionUsuarioService;
import foro_hub.api_foro.domain.usuarios.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
    @Autowired
    private TopicoService topicoService;
    @Autowired
    AutenticacionUsuarioService autenticacionUsuarioService;

    @PostMapping
    @Transactional
    public ResponseEntity<RegistroTopicoResponse> registrarTopico(@RequestBody @Valid RegistroTopicoRequest datos) {
        System.out.println(datos);
        Usuario usuarioAutenticado = autenticacionUsuarioService.obtenerUsuarioAutenticado();
        RegistroTopicoResponse response = topicoService.registrarTopico(datos, usuarioAutenticado);
        return ResponseEntity.ok(response);
    }
}
