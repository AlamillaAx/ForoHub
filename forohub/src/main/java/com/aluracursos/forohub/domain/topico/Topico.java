package com.aluracursos.forohub.domain.topico;

import com.aluracursos.forohub.domain.curso.Curso;
import com.aluracursos.forohub.domain.respuesta.Respuesta;
import com.aluracursos.forohub.domain.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Table(name="topicos")
@Entity(name="Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private Boolean status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario autor;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="curso_id")
    private Curso curso;
    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Respuesta> respuestas;

    public Topico(DatosRegistroTopico datosRegistroTopico, Usuario autor, Curso curso) {
        this.titulo = datosRegistroTopico.titulo();
        this.mensaje = datosRegistroTopico.mensaje();
        this.fechaCreacion = datosRegistroTopico.fechaCreacion();
        this.status = true;
        this.autor = autor;
        this.curso = curso;
    }

    public void actualizarTopicoConCurso(DatosActualizarTopico datosActualizarTopico, Curso curso){
        if (datosActualizarTopico.titulo() != null && !datosActualizarTopico.titulo().isEmpty()){
            this.titulo = datosActualizarTopico.titulo();
        }
        if (datosActualizarTopico.mensaje() != null && !datosActualizarTopico.mensaje().isEmpty()){
            this.mensaje = datosActualizarTopico.mensaje();
        }
        if (datosActualizarTopico.curso_id() != null){
            this.curso = curso;
        }
    }

    public void actualizarTopicoSinCurso(DatosActualizarTopico datosActualizarTopico){
        if (datosActualizarTopico.titulo() != null && !datosActualizarTopico.titulo().isEmpty()){
            this.titulo = datosActualizarTopico.titulo();
        }
        if (datosActualizarTopico.mensaje() != null && !datosActualizarTopico.mensaje().isEmpty()){
            this.mensaje = datosActualizarTopico.mensaje();
        }
    }

    public void actualizarTopicoStatus(boolean status){
        this.status = status;
    }
}
