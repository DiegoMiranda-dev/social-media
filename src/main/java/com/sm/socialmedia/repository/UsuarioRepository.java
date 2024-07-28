package com.sm.socialmedia.repository;

import com.sm.socialmedia.user.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByEmail(String email);

    boolean existsByPasswordHash(String password);

    Optional<Usuario> findUserByNombreUsuario(String nombreUsuario);
}
