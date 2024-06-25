package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.usuario.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")

public class UsuariosController {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registro")
    public ResponseEntity<DatosRespuestaUsuario> registrarNuevoUsuario(@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario, UriComponentsBuilder uriComponentsBuilder){
        Usuario nuevoUsuario = usuarioRepository.save(new Usuario(datosRegistroUsuario));
        DatosRespuestaUsuario datosRespuestaUsuario = new DatosRespuestaUsuario(nuevoUsuario);
        URI url =uriComponentsBuilder.path("cursos/{id}").buildAndExpand(nuevoUsuario.getId()).toUri();
    return ResponseEntity.created(url).body(datosRespuestaUsuario);
    }

    @PutMapping("/recuperacion")
    @Transactional
    public ResponseEntity<String> recuperarContrasena(@RequestBody @Valid DatosActualizacionUsuario datosActualizacionUsuario, UriComponentsBuilder uriComponentsBuilder){
        var response = usuarioService.actualizacionContrasena(datosActualizacionUsuario);
        return ResponseEntity.ok(response);
    }
}
