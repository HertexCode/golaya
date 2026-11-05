package com.devPortes.users.dto.auth;

public record NewUserResponseDto(
        Long id,
        String nameUser,
        String email,
        String urlPicture,
        String token
) {
}