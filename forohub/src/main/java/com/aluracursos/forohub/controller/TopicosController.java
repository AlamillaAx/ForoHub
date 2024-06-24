package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.curso.CursoRepository;
import com.aluracursos.forohub.domain.topico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
public class TopicosController {
    @Autowired
    CursoRepository cursoRepository;

    @Autowired
    TopicosService service;
    @Autowired
    TopicoRepository topicoRepository;

    @PostMapping("/nuevo")
    public ResponseEntity<DatosRespuestaTopico> nuevoTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico, UriComponentsBuilder uriComponentsBuilder){
        var reponse =service.registrar(datosRegistroTopico);
        return ResponseEntity.ok(reponse);
    }

    @GetMapping("/todos")
    public ResponseEntity<Page<DatosRespuestaTopico>> listadoTopicos(Pageable paginacion){
        return ResponseEntity.ok(topicoRepository.findAll(paginacion).map(DatosRespuestaTopico::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> consultaTopico(@PathVariable Long id){
        var response = service.busquedaPorId(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> actualizacionTopico(@PathVariable Long id, @RequestBody DatosActualizarTopico datosActualizarTopico){
        var response = service.actualizaPorId(id, datosActualizarTopico);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/eliminar/{id}")
    @Transactional
    public ResponseEntity<String> eliminacionTopico(@PathVariable Long id){
        var response = service.eliminarTopicoPorId(id);
        return ResponseEntity.ok(response);
    }
}
