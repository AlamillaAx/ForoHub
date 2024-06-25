package com.aluracursos.forohub.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DatosActualizacionUsuario(
        @NotBlank
        @Email
        String email,
        @NotBlank
        String nuevaContrasena
) {
}
