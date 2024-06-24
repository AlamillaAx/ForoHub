package com.aluracursos.forohub.domain.respuesta;

import com.aluracursos.forohub.domain.topico.DatosRegistroTopico;
import com.aluracursos.forohub.domain.topico.DatosRespuestaTopico;
import com.aluracursos.forohub.domain.topico.Topico;
import com.aluracursos.forohub.domain.topico.TopicoRepository;
import com.aluracursos.forohub.domain.usuario.UsuarioRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class RespuestasService {
    @Autowired
    RespuestaRepository respuestaRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    TopicoRepository topicoRepository;


    public DatosRespuestaRespuesta registrar(DatosRegistroRespuesta datosRegistroRespuesta){
        if (!usuarioRepository.findById(datosRegistroRespuesta.autor_id()).isPresent()){
            throw new ValidationException("No se encontro un usuario con el id proporcionado");
        }
        if (!topicoRepository.findById(datosRegistroRespuesta.topico_id()).isPresent()){
            throw new ValidationException("No se encuentro ninguna respuesta con el id proporcionado");
        }
        var autor = usuarioRepository.findById(datosRegistroRespuesta.autor_id()).get();
        var topico = topicoRepository.findById(datosRegistroRespuesta.topico_id()).get();

        var nuevaRespuesta = new Respuesta(datosRegistroRespuesta, autor, topico);
        respuestaRepository.save(nuevaRespuesta);
        return new DatosRespuestaRespuesta(nuevaRespuesta);
    }

    public Page<DatosRespuestaRespuesta> listadoRespuestasPorPregunta(Pageable paginacion, Long id){
        if (!topicoRepository.findById(id).isPresent()){
            throw new ValidationException("No se encuentro ninguna respuesta con el id proporcionado");
        }
        return respuestaRepository.findByTopicoId(id, paginacion).map(DatosRespuestaRespuesta::new);
    }

    public String eliminarRespuestaPorId(Long id){
        if (!respuestaRepository.existsById(id)){
            throw new ValidationException("No existe una respuesta con el id proporcionado!");
        }
        else {
            Respuesta respuesta = respuestaRepository.getReferenceById(id);
            respuestaRepository.delete(respuesta);
            return "La respuesta fue eliminada exitosamente!";
        }
    }

    public DatosRespuestaRespuesta actualizarRespuestaPorId(DatosActualizacionRespuesta datosActualizacionRespuesta){
        if (!respuestaRepository.existsById(datosActualizacionRespuesta.id())){
            throw new ValidationException("No existe una respuesta con el id proporcionado!");
        }else {
            Respuesta respuesta = respuestaRepository.getReferenceById(datosActualizacionRespuesta.id());
            respuesta.actualizarRespuesta(datosActualizacionRespuesta);
            return new DatosRespuestaRespuesta(respuesta);
        }
    }
}
