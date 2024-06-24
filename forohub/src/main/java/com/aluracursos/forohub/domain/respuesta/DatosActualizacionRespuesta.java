package com.aluracursos.forohub.domain.respuesta;

public record DatosActualizacionRespuesta(
        Long id,
        String mensaje,
        boolean solucion
) {
}
