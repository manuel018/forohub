package com.forohub.service;

import com.forohub.dto.UsuarioDTO;
import com.forohub.dto.UsuarioUpdateDTO;
import com.forohub.model.Usuario;
import com.forohub.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario getUsuarioById(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("No existe el usuario")
        );
    }

    public Usuario getUsuarioByEmail(String email) {
        return usuarioRepository.findByCorreo(email).orElseThrow(() ->
                new EntityNotFoundException("No existe el usuario")
        );
    }

    public void deleteUser(Long id) {
        usuarioRepository.delete(getUsuarioById(id));
    }

    @Transactional
    public Usuario updateUser(UsuarioUpdateDTO dto, Long id) {
        Usuario usuario = getUsuarioById(id);

        if (dto.nombre() != null && !dto.nombre().isBlank()) {
            usuario.setNombre(dto.nombre());
        }

        if (dto.correo() != null && !dto.correo().isBlank()) {
            usuario.setCorreo(dto.correo());
        }

        if (dto.password() != null && !dto.password().isBlank()) {
            usuario.setPassword(passwordEncoder.encode(dto.password()));
        }

        if (dto.perfiles() != null && !dto.perfiles().isBlank()) {
            usuario.setPerfiles(dto.perfiles());
        }

        return usuario;
    }

    public Usuario add(UsuarioDTO usuarioDTO) {
        return usuarioRepository.save(transformToEntity(usuarioDTO));
    }

    private Usuario transformToEntity(UsuarioDTO dto) {
        return Usuario.builder()
                .nombre(dto.nombre())
                .correo(dto.correo())
                .password(passwordEncoder.encode(dto.password()))
                .password(dto.password())
                .perfiles(dto.perfiles())
                .build();
    }
}
