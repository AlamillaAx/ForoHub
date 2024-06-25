package com.aluracursos.forohub.domain.usuario;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByEmail(String username);

    @Query("""
            SELECT u
            FROM Usuario u 
            WHERE u.email = :email""")
    Usuario busquedaPorEmail(String email);
}
