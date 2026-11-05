package com.devPortes.users.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EditProfileRequestDto(
        @NotBlank(message = "El nombre es requerido")
        @Size(min = 2, max = 80, message = "El nombre debe tener entre 2 y 80 caracteres")
        String name,

        @NotBlank(message = "El correo es requerido")
        @Email(message = "Correo no válido")
        String email,

        @NotBlank(message = "El teléfono es requerido")
        String phoneNumber,

        @NotBlank(message = "La cédula es requerida")
        String identityDocument
) {
}
