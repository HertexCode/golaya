package com.devPortes.reservations.dto;

import java.time.LocalTime;
import java.util.List;

public record AvailableDatesTimesResponseDto(
        List<LocalTime> reservationHours
) {
}
