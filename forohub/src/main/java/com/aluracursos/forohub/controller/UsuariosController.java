package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.usuario.DatosRegistroUsuario;
import com.aluracursos.forohub.domain.usuario.DatosRespuestaUsuario;
import com.aluracursos.forohub.domain.usuario.Usuario;
import com.aluracursos.forohub.domain.usuario.UsuarioRepository;
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

    @PostMapping("/registro")
    public ResponseEntity<DatosRespuestaUsuario> registrarNuevoUsuario(@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario, UriComponentsBuilder uriComponentsBuilder){
        Usuario nuevoUsuario = usuarioRepository.save(new Usuario(datosRegistroUsuario));
        DatosRespuestaUsuario datosRespuestaUsuario = new DatosRespuestaUsuario(nuevoUsuario);
        URI url =uriComponentsBuilder.path("cursos/{id}").buildAndExpand(nuevoUsuario.getId()).toUri();
    return ResponseEntity.created(url).body(datosRespuestaUsuario);
    }
}
