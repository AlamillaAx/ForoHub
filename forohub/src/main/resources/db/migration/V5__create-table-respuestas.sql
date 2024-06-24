create table respuestas(
    id bigint not null auto_increment,
    mensaje varchar(100) not null,
    topico_id bigint not null unique,
    fecha_creacion datetime not null,
    autor_id bigint not null,
    solucion tinyint not null,

    primary key(id),

    constraint fk_respuestas_topicos_id foreign key (topico_id) references topicos(id),
    constraint fk_respuestas_usuarios_id foreign key (autor_id) references usuarios(id)
);