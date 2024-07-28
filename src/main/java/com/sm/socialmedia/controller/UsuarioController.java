package com.sm.socialmedia.controller;

import com.sm.socialmedia.response.ApiResponse;
import com.sm.socialmedia.service.UsuarioService;
import com.sm.socialmedia.user.DatosLoginUsuario;
import com.sm.socialmedia.user.DatosRegistroUsuario;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pag")
public class UsuarioController {

    private final UsuarioService usuarioService;


    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/registro")
    public ResponseEntity<ApiResponse> registrarUsuario(@RequestBody @Validated DatosRegistroUsuario datosRegistroUsuario, BindingResult result) {
        ApiResponse response = usuarioService.registrarUsuario(datosRegistroUsuario, result);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUsuario(@RequestBody @Validated DatosLoginUsuario datosLoginUsuario) {
        ApiResponse response = usuarioService.loginUsuario(datosLoginUsuario);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "holaaaaaaaaaaaaaaa";
    }
}
