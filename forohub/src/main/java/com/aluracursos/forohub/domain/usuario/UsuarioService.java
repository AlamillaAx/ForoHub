package com.aluracursos.forohub.domain.usuario;

import com.aluracursos.forohub.domain.topico.DatosRespuestaTopico;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public String actualizacionContrasena(DatosActualizacionUsuario datosActualizacionUsuario){
        Usuario usuario = usuarioRepository.busquedaPorEmail(datosActualizacionUsuario.email());

        if (usuario == null) {
            throw new ValidationException("No se encontró un usuario con el email ingresado!");
        }

        usuario.actualizarContrasena(datosActualizacionUsuario.nuevaContrasena());
        return "Contraseña actualizada con éxito!";
    }
}
