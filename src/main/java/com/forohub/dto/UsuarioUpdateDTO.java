package com.forohub.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UsuarioUpdateDTO(
        String nombre,

        @Email(message = "El formato del correo es inválido")
        String correo,

        @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
        String password,

        String perfiles
) {
}