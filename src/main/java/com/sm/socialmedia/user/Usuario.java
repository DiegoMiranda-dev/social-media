package com.sm.socialmedia.user;

import lombok.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Table(name = "usuarios")
@Entity(name = "usuario")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Nombre no puede estar vacío")
    private String nombre;
    @NotBlank(message = "Apellido no puede estar vacío")
    private String apellido;
    @NotBlank(message = "Nombre de usuario no puede estar vacío")
    private String nombreUsuario;
    @NotBlank(message = "Email no puede estar vacío")
    @Column(unique = true)
    @Email(message = "El nombreUsuario no es válido")
    private String email;
    @NotBlank(message = "Contraseña no puede estar vacío")
    private String passwordHash;
    @Column(name = "is_enabled")
    private Boolean isEnabled;
    @Column(name = "account_expired")
    private Boolean accountExpired;
    @Column(name = "account_locked")
    private Boolean accountLocked;
    @Column(name = "credentials_expired")
    private Boolean credentialsExpired;


    public Usuario(DatosRegistroUsuario datosRegistroUsuario) {
        this.id = datosRegistroUsuario.id();
        this.nombre = datosRegistroUsuario.nombre();
        this.apellido = datosRegistroUsuario.apellido();
        this.nombreUsuario = datosRegistroUsuario.nombreUsuario();
        this.email = datosRegistroUsuario.email();
        this.passwordHash = datosRegistroUsuario.passwordHash();
        this.isEnabled = true;
        this.accountExpired = false;
        this.accountLocked = false;
        this.credentialsExpired = false;
    }


}
