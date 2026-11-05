package com.devPortes.users.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record NewUserRequestDto(
        @NotBlank(message = "Debes ingresar un nombre valido, no puede venir vacio")
        String name,
        @NotBlank(message = "El documento de identidad no puede venir vacio")
        String identityDocument,
        @NotBlank(message = "Debes ingresar un numero de telefono correcto")
        String phoneNumber,
        @Email
        String email,
        @NotBlank(message = "Debes ingresar una contraseña correcta")
        String password) {
}