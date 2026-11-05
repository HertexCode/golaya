package com.devPortes.location.dto;

public record UpdateLocationRequestDto(
        String name,
        String headquarters,
        String address,
        String urlQrAddress,
        String description
) {
}