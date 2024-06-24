package com.aluracursos.forohub.domain.respuesta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosRegistroRespuesta(
        @NotBlank
        String mensaje,
        @NotNull
        Long topico_id,
        LocalDateTime fechaCreacion,
        @NotNull
        Long autor_id
) {
}
