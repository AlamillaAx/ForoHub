create table usuarios(
    id bigint not null auto_increment,
    nombre varchar(100) not null,
    email varchar(100) not null unique,
    contrasena varchar(100) not null,
    perfil_id bigint not null,

    primary key(id),

    constraint fk_usuarios_perfiles_id foreign key (perfil_id) references perfiles(id)
);