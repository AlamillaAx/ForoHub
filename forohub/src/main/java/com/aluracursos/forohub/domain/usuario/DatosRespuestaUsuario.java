package com.aluracursos.forohub.domain.usuario;

public record DatosRespuestaUsuario(
        Long id,
        String nombre

) {
    public DatosRespuestaUsuario(Usuario nuevoUsuario) {
        this(nuevoUsuario.getId(), nuevoUsuario.getNombre());
    }
}
