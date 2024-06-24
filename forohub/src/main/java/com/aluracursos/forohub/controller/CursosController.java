package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.curso.DatosRegistroCurso;
import com.aluracursos.forohub.domain.curso.DatosRespuestaCurso;
import com.aluracursos.forohub.domain.curso.Curso;
import com.aluracursos.forohub.domain.curso.CursoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/cursos")

public class CursosController {
    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping("/registro")
    public ResponseEntity<DatosRespuestaCurso> registrarNuevoCurso(@RequestBody @Valid DatosRegistroCurso datosRegistroCurso, UriComponentsBuilder uriComponentsBuilder){
        Curso nuevoCurso = cursoRepository.save(new Curso(datosRegistroCurso));
        DatosRespuestaCurso datosRespuestaCurso = new DatosRespuestaCurso(nuevoCurso);
        URI url =uriComponentsBuilder.path("cursos/{id}").buildAndExpand(nuevoCurso.getId()).toUri();
    return ResponseEntity.created(url).body(datosRespuestaCurso);
    }

    @GetMapping
    public ResponseEntity<Page<DatosRespuestaCurso>> listadoCursos(Pageable paginacion){
        return ResponseEntity.ok(cursoRepository.findAll(paginacion).map(DatosRespuestaCurso::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaCurso> consultaCurso(@PathVariable Long id){
        Curso cursoBuscado = cursoRepository.getReferenceById((id));
        var datosCurso = new DatosRespuestaCurso(cursoBuscado);
        return ResponseEntity.ok(datosCurso);
    }
}
