package com.sm.socialmedia.service;

import com.sm.socialmedia.repository.UsuarioRepository;
import com.sm.socialmedia.user.Usuario;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UserDetailsServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findUserByNombreUsuario(username).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        List<String> roles = usuario.getRoles().stream()
                .map(r -> "ROLE_" + r.getRol().name())
                .toList();

        return User.builder()
                .username(usuario.getNombreUsuario())
                .password(usuario.getPasswordHash())
                .roles(roles.toArray(new String[0]))
                .build();
    }

}
