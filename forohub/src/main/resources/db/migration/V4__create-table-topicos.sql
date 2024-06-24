create table topicos(
    id bigint not null auto_increment,
    titulo varchar(100) not null,
    mensaje varchar(100) not null unique,
    fecha_creacion datetime not null,
    status tinyint not null,
    autor_id bigint not null,
    curso_id bigint not null,

    primary key(id),

    constraint fk_topicos_usuarios_id foreign key (autor_id) references usuarios(id),
    constraint fk_topicos_cursos_id foreign key (curso_id) references cursos(id)
);