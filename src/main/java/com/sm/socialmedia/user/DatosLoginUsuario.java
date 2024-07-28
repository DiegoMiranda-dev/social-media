package com.sm.socialmedia.user;

public record DatosLoginUsuario(
        String nombreUsuario,
        String passwordHash
) {
    public String nombreUsuario() {
        return nombreUsuario;
    }

    @Override
    public String passwordHash() {
        return passwordHash;
    }
}

