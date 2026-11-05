package com.devPortes.statistics.dto;

public record UserStatisticsResponseDto(
        long totalReservations,
        long playedHours,
        String favoriteField,
        long favoriteFieldHours
) {
}
