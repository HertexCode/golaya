package com.devPortes.users.dto.client;

public record ListUsersResponseDto(
    Long id,
    String urlPicture,
    String name,
    String email,
    String identityDocument,
    String phoneNumber,
    String role,
    String classification,
    boolean state
) {}
