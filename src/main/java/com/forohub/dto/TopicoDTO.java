package com.forohub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record TopicoDTO(

        @NotBlank
        String titulo,

        @NotBlank
        String mensaje,

        @NotBlank
        @Pattern(regexp = "activo|inactivo", message = "Solo se permite 'activo' o 'inactivo'")
        String status,

        @NotBlank
        String autor,

        @NotBlank
        String curso) {
}
