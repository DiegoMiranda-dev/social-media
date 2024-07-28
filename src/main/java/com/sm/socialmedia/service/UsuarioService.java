package com.sm.socialmedia.service;

import com.sm.socialmedia.infra.errors.DatosErrorValidacion;
import com.sm.socialmedia.repository.UsuarioRepository;
import com.sm.socialmedia.response.ApiResponse;
import com.sm.socialmedia.user.DatosLoginUsuario;
import com.sm.socialmedia.user.DatosRegistroUsuario;
import com.sm.socialmedia.user.Usuario;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ApiResponse registrarUsuario(DatosRegistroUsuario datosRegistroUsuario, BindingResult result) {
        if (result.hasErrors()) {
            List<DatosErrorValidacion> errores = result.getAllErrors().stream()
                    .map(error -> new DatosErrorValidacion(error.getDefaultMessage()))
                    .collect(Collectors.toList());
            return new ApiResponse(false, null, errores);
        } else if (usuarioRepository.existsByEmail(datosRegistroUsuario.email())) {
            return new ApiResponse(false, "El email ya está registrado", null);
        } else if (!datosRegistroUsuario.passwordHash().equals(datosRegistroUsuario.confirmarPasswordHash())) {
            return new ApiResponse(false, "Las contraseñas no coinciden", null);
        } else {
            Usuario usuario = new Usuario();
            usuario.setNombre(datosRegistroUsuario.nombre());
            usuario.setApellido(datosRegistroUsuario.apellido());
            usuario.setNombreUsuario(datosRegistroUsuario.nombreUsuario());
            usuario.setEmail(datosRegistroUsuario.email());
            usuario.setPasswordHash(passwordEncoder.encode(datosRegistroUsuario.passwordHash()));
            usuario.setIsEnabled(true);
            usuario.setAccountExpired(false);
            usuario.setAccountLocked(false);
            usuario.setCredentialsExpired(false);

            usuarioRepository.save(usuario);
            return new ApiResponse(true, "Registrado con exito :)", null);
        }
    }

    public ApiResponse loginUsuario(DatosLoginUsuario datosLoginUsuario) {
        Optional<Usuario> usuarioRepo = usuarioRepository.findUserByNombreUsuario(datosLoginUsuario.nombreUsuario());
        if (usuarioRepo.isEmpty()) {
            return new ApiResponse(false, "Usuario no encontrado :(", null);
        }

        Usuario usuario = usuarioRepo.get();
        if (passwordEncoder.matches(datosLoginUsuario.passwordHash(), usuario.getPasswordHash())) {
            return new ApiResponse(true, "Sesión iniciada con éxito :)", null);
        }

        return new ApiResponse(false, "Contraseña incorrecta :(", null);
    }

}
