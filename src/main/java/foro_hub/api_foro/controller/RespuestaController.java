package foro_hub.api_foro.controller;

import foro_hub.api_foro.domain.respuesta.DatosRespuestaResponse;
import foro_hub.api_foro.domain.respuesta.RegistroRespuestaRequest;
import foro_hub.api_foro.domain.respuesta.RegistroRespuestaResponse;
import foro_hub.api_foro.domain.respuesta.RespuestaService;
import foro_hub.api_foro.domain.usuarios.AutenticacionUsuarioService;
import foro_hub.api_foro.domain.usuarios.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/respuestas")
public class RespuestaController {

    @Autowired
    RespuestaService respuestaService;
    @Autowired
    AutenticacionUsuarioService autenticacionUsuarioService;

    @PostMapping("/topicos/{id}")
    public ResponseEntity<RegistroRespuestaResponse> crearRespuesta(@PathVariable(name = "id") Long topicoId, @RequestBody @Valid RegistroRespuestaRequest respuesta) {
        System.out.println(respuesta);
        Usuario usuarioAutenticado = autenticacionUsuarioService.obtenerUsuarioAutenticado();
        RegistroRespuestaResponse response = respuestaService.crearRespuesta(topicoId, respuesta, usuarioAutenticado);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaResponse> obtenerRespuestaPorId(@PathVariable Long id) {
        DatosRespuestaResponse respuesta = respuestaService.obtenerRespuestaPorId(id);
        return ResponseEntity.ok(respuesta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegistroRespuestaResponse> modificarRespuesta(@PathVariable Long id, @RequestBody @Valid RegistroRespuestaRequest respuesta) {
        Usuario usuarioAutenticado = autenticacionUsuarioService.obtenerUsuarioAutenticado();
        RegistroRespuestaResponse response = respuestaService.modificarRespuesta(id, respuesta, usuarioAutenticado);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRespuesta(@PathVariable Long id) {
        Usuario usuarioAutenticado = autenticacionUsuarioService.obtenerUsuarioAutenticado();
        respuestaService.eliminarRespuesta(id, usuarioAutenticado);
        return ResponseEntity.noContent().build();
    }

    // Trae todas las respuestas generadas por el usuario autenticado
    @GetMapping
    public ResponseEntity<List<DatosRespuestaResponse>> obtenerTodasLasRespuestas() {
        Usuario usuarioAutenticado = autenticacionUsuarioService.obtenerUsuarioAutenticado();
        return ResponseEntity.ok(respuestaService.obtenerRespuestas(usuarioAutenticado.getId()));
    }
}