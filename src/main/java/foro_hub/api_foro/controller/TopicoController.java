package foro_hub.api_foro.controller;

import foro_hub.api_foro.domain.topico.*;
import foro_hub.api_foro.domain.usuarios.AutenticacionUsuarioService;
import foro_hub.api_foro.domain.usuarios.Usuario;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<DetalleTopicoResponse>> listarTopicos() {
        List<DetalleTopicoResponse> response = topicoService.listarTopicos();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleTopicoResponse> obtenerTopico(@PathVariable @NotNull Long id) {
        DetalleTopicoResponse response = topicoService.obtenerTopico(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ModificacionTopicoResponse> modificarTopico(@PathVariable Long id, @RequestBody ModificacionTopicoRequest datosModificacion) {
        Usuario usuarioAutenticado = autenticacionUsuarioService.obtenerUsuarioAutenticado();
        ModificacionTopicoResponse response = topicoService.actualizarTopico(id, datosModificacion, usuarioAutenticado);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        Usuario usuarioAutenticado = autenticacionUsuarioService.obtenerUsuarioAutenticado();
        topicoService.eliminarTopico(id, usuarioAutenticado);
        return ResponseEntity.ok().build();
    }
}
