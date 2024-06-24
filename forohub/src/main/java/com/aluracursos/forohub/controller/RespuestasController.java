package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.respuesta.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/respuestas")
public class RespuestasController {
    @Autowired
    private RespuestasService respuestasService;
    @Autowired
    private RespuestaRepository respuestaRepository;

    @PostMapping("/nueva-respuesta")
    public ResponseEntity<DatosRespuestaRespuesta> nuevaRespuesta(@RequestBody @Valid DatosRegistroRespuesta datosRegistroRespuesta){
        var response = respuestasService.registrar(datosRegistroRespuesta);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<Page<DatosRespuestaRespuesta>> listadoRespuestas(Pageable paginacion, @PathVariable Long id){
        var response = respuestasService.listadoRespuestasPorPregunta(paginacion, id);
        return ResponseEntity.ok(respuestaRepository.findAll(paginacion).map(DatosRespuestaRespuesta::new));
    }

    @DeleteMapping("/eliminar-respuesta/{id}")
    @Transactional
    public ResponseEntity<String> eliminacionRespuesta(@PathVariable Long id){
        var response = respuestasService.eliminarRespuestaPorId(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/actualizar")
    @Transactional
    public ResponseEntity<DatosRespuestaRespuesta> actualizarRespuesta(@RequestBody @Valid DatosActualizacionRespuesta datosActualizacionRespuesta){
        var response = respuestasService.actualizarRespuestaPorId(datosActualizacionRespuesta);
        return ResponseEntity.ok(response);
    }
}
