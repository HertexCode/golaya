package com.devPortes.statistics.dto;

public record PublicStatisticsResponseDto(
        long availableFields,
        long sports,
        long locations,
        boolean onlineBooking
) {
}
