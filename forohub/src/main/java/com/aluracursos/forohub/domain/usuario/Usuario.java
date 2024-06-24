package com.aluracursos.forohub.domain.usuario;


import com.aluracursos.forohub.domain.perfil.DatosRegistroPerfil;
import com.aluracursos.forohub.domain.perfil.Perfil;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="usuarios")
@Entity(name="Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Usuario{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String contrasena;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="perfil_id")
    private Perfil perfil;

    public Usuario(DatosRegistroUsuario datosRegistroUsuario) {
        this.nombre = datosRegistroUsuario.nombre();
        this.email = datosRegistroUsuario.email();
        this.contrasena = datosRegistroUsuario.contrasena();//convertToBcrypt(datosRegistroUsuario.contrasena());
        this.perfil = new Perfil(datosRegistroUsuario.nombre());
    }

//    public String convertToBcrypt(String rawPassword){
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        return passwordEncoder.encode(rawPassword);
//    }

}
