package foro_hub.api_foro.controller;

import foro_hub.api_foro.domain.usuarios.DatosAutenticacionUsuario;
import foro_hub.api_foro.domain.usuarios.Usuario;
import foro_hub.api_foro.infra.security.DatosJWTtoken;
import foro_hub.api_foro.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datos) {
        Authentication token = new UsernamePasswordAuthenticationToken(datos.email(), datos.clave());
        var usuarioAutenticado = authenticationManager.authenticate(token);
        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJWTtoken(JWTtoken));
    }
}
