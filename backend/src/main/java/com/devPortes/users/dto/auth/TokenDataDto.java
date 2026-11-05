package com.devPortes.users.dto.auth;

public record TokenDataDto(
        String token,
        String nameUser,
        String email,
        String identityDocument,
        String phoneNumber
) {
}
