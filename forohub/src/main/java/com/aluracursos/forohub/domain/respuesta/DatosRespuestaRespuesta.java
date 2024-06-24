package com.aluracursos.forohub.domain.respuesta;

public record DatosRespuestaRespuesta(
        Long id,
        String mensaje,
        Long autor_id,
        Boolean solucion
) {
    public DatosRespuestaRespuesta(Respuesta nuevaRespuesta) {
        this(nuevaRespuesta.getId(), nuevaRespuesta.getMensaje(), nuevaRespuesta.getUsuario().getId(), nuevaRespuesta.isSolucion());
    }
}
