package com.devPortes.reservations.dto;

import java.util.List;

public record AvailableDatesResponseDto(
        List<String> availableDates,
        List<String> fullDates,
        List<String> datesWithReservations
) {
}
