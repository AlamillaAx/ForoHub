package com.aluracursos.forohub.domain.respuesta;

import com.aluracursos.forohub.domain.topico.Topico;
import com.aluracursos.forohub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name="respuestas")
@Entity(name="Respuesta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;
    @ManyToOne
    @JoinColumn(name = "topico_id")
    private Topico topico;
    private LocalDateTime fechaCreacion;
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Usuario usuario;
    private boolean solucion;

    public Respuesta(DatosRegistroRespuesta datosRegistroRespuesta, Usuario autor, Topico topico) {
        this.mensaje = datosRegistroRespuesta.mensaje();
        this.topico = topico;
        this.fechaCreacion = datosRegistroRespuesta.fechaCreacion();
        this.usuario = autor;
        this.solucion = false;
    }

    public void actualizarRespuesta(DatosActualizacionRespuesta datosActualizacionRespuesta){
        if (datosActualizacionRespuesta.mensaje() != null && !datosActualizacionRespuesta.mensaje().isEmpty()){
            this.mensaje = datosActualizacionRespuesta.mensaje();
        }
        if (datosActualizacionRespuesta.solucion()){
            this.solucion = true;
            this.topico.actualizarTopicoStatus(false);
        }else {
            this.solucion = false;
            this.topico.actualizarTopicoStatus(true);
        }
    }
}
