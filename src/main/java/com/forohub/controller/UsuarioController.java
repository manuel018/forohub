package com.forohub.controller;

import com.forohub.dto.ApiResponse;
import com.forohub.dto.UsuarioUpdateDTO;
import com.forohub.model.Usuario;
import com.forohub.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<Usuario>> getTopic(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(this.usuarioService.getUsuarioById(id), "Encontrado", true));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Usuario>>> getAllTopics() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(this.usuarioService.getAllUsuarios(), "Encontrado", true));
    }

    @PatchMapping("{id}")
    public ResponseEntity<ApiResponse<Usuario>> patchTopic(@PathVariable Long id, @Valid @RequestBody UsuarioUpdateDTO topicoDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(this.usuarioService.updateUser(topicoDto, id), "Patcheado", true));
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse<Usuario>> putTopic(@PathVariable Long id, @Valid @RequestBody UsuarioUpdateDTO topicoDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(this.usuarioService.updateUser(topicoDto, id), "Actualizado completamente", true));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable Long id) {
        this.usuarioService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }
}