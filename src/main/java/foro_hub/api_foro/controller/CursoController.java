package foro_hub.api_foro.controller;

import foro_hub.api_foro.domain.curso.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @PostMapping
    public ResponseEntity<RegistroCursoResponse> crearCurso(@RequestBody @Valid RegistroCursoRequest registroCursoRequest) {
        RegistroCursoResponse response = cursoService.crearCurso(registroCursoRequest);
        return ResponseEntity.status(201).body(response);
    }
    @GetMapping
    public ResponseEntity<List<DatosCursoResponse>> listarCursos() {
        List<DatosCursoResponse> cursos = cursoService.listarCursos();
        return ResponseEntity.ok(cursos);
    }
    @PutMapping("/{id}")
    public ResponseEntity<RegistroCursoResponse> modificarCurso(@PathVariable Long id, @RequestBody @Valid ModificacionCursoRequest modificacionCursoRequest) {
        RegistroCursoResponse response = cursoService.modificarCurso(id, modificacionCursoRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCurso(@PathVariable Long id) {
        cursoService.eliminarCurso(id);
        return ResponseEntity.noContent().build();
    }
}