package com.aluracursos.forohub.domain.curso;

import com.aluracursos.forohub.domain.topico.Topico;
import com.aluracursos.forohub.domain.usuario.Usuario;

public record DatosRespuestaCurso(
    Long id,
    String nombre,
    Categoria categoria
) {
    public DatosRespuestaCurso(Curso curso){
        this(curso.getId(), curso.getNombre(), curso.getCategoria());
    }

}
