package com.aluracursos.forohub.domain.topico;

public record DatosRespuestaTopico(
    Long id,
    String titulo,
    String mensaje
) {

    public DatosRespuestaTopico(Topico nuevoTopico) {
        this(nuevoTopico.getId(),nuevoTopico.getTitulo(), nuevoTopico.getMensaje());
    }
}
