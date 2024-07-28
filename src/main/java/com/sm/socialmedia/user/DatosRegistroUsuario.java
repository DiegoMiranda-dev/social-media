package com.sm.socialmedia.user;

public record DatosRegistroUsuario(
        Long id,
        String nombre,
        String apellido,
        String nombreUsuario,
        String email,
        String passwordHash,
        String confirmarPasswordHash
) {

    @Override
    public String nombreUsuario() {
        return nombreUsuario;
    }

    @Override
    public String nombre() {
        return nombre;
    }

    @Override
    public String apellido() {
        return apellido;
    }

    @Override
    public String email() {
        return email;
    }

    @Override
    public String passwordHash() {
        return passwordHash;
    }

    public String confirmarPasswordHash() {
        return confirmarPasswordHash;
    }


}
