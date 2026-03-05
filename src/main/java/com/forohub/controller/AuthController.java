package com.forohub.controller;

import com.forohub.dto.ApiResponse;
import com.forohub.dto.AuthResponse;
import com.forohub.dto.LoginDTO;
import com.forohub.dto.UsuarioDTO;
import com.forohub.model.Usuario;
import com.forohub.service.AuthService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("login")
    public ResponseEntity<ApiResponse<AuthResponse>> Login(@Valid @RequestBody LoginDTO loginDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(authService.login(loginDTO), "Usuario logeado", true));
    }

    @PostMapping("register")
    public ResponseEntity<AuthResponse> saveTopic(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        authService.register(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(authService.register(usuarioDTO));
    }
}
