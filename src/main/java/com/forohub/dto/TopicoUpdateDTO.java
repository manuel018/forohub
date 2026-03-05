package com.forohub.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record TopicoUpdateDTO(
        @Size(min = 5, max = 100) String titulo,
        String mensaje,
        @Pattern(regexp = "activo|inactivo", message = "Status debe ser 'activo' o 'inactivo'")
        String status,
        String autor,
        String curso
) {
}