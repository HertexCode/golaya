package com.devPortes.location.dto;

public record NewLocationRequestDto(
        String name,
        String headquarters,
        String address,
        String urlQrAddress,
        String description

) {
}
