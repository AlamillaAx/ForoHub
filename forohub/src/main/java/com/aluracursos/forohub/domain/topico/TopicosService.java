package com.aluracursos.forohub.domain.topico;

import com.aluracursos.forohub.domain.curso.CursoRepository;
import com.aluracursos.forohub.domain.usuario.UsuarioRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicosService {

    @Autowired
    TopicoRepository topicoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    CursoRepository cursoRepository;

    public DatosRespuestaTopico registrar(DatosRegistroTopico datosRegistroTopico){
        if (!usuarioRepository.findById(datosRegistroTopico.autor_id()).isPresent()){
            throw new ValidationException("No se encontro un usuario con el id proporcionado");
        }

        if (datosRegistroTopico.curso_id() != null && !cursoRepository.existsById(datosRegistroTopico.curso_id())){
            throw new ValidationException("No fue encontrado un curso con este id");
        }

        var autor = usuarioRepository.findById(datosRegistroTopico.autor_id()).get();
        var curso = cursoRepository.findById(datosRegistroTopico.curso_id()).get();

        var nuevoTopico = new Topico(datosRegistroTopico, autor, curso);
        topicoRepository.save(nuevoTopico);
        return new DatosRespuestaTopico(nuevoTopico);
    }

    public DatosRespuestaTopico busquedaPorId(Long id){
        if(!topicoRepository.findById(id).isPresent()){
            throw new ValidationException("No existe un topico con el id ingresado!");
        }
        if(id == null){
            throw new ValidationException("Debe introducir un id para realizar la busqueda");
        }
        var topicoBuscado = topicoRepository.getReferenceById((id));
        return new DatosRespuestaTopico(topicoBuscado);
    }

    public DatosRespuestaTopico actualizaPorId(Long id, DatosActualizarTopico datosActualizarTopico){
        if(!topicoRepository.findById(id).isPresent()){
            throw new ValidationException("No existe un topico con el id ingresado!");
        }

        Topico topico = topicoRepository.getReferenceById(id);

        if (datosActualizarTopico.curso_id() != null){
            var curso = cursoRepository.findById(datosActualizarTopico.curso_id()).get();
            topico.actualizarTopicoConCurso(datosActualizarTopico, curso);
        } else{
            topico.actualizarTopicoSinCurso(datosActualizarTopico);
        }

        return new DatosRespuestaTopico(topico.getId(), topico.getTitulo(), topico.getMensaje());
    }

    public String eliminarTopicoPorId(Long id) {
        if (!topicoRepository.existsById(id)){
            throw new ValidationException("No existe una pregunta con el id proporcionado!");
        }
        else {
            Topico topico = topicoRepository.getReferenceById(id);
            topicoRepository.delete(topico);
            return "La pregunta fue eliminada exitosamente!";
        }
    }
}
