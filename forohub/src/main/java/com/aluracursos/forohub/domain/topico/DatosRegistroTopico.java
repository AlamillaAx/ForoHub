package com.aluracursos.forohub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosRegistroTopico(
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        @NotNull
        Long autor_id,
        @NotNull
        Long curso_id
) {
}
