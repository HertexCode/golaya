package com.devPortes.reservations.dto;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public record NewReservationRequestDto(
        Long userId,
        Long fieldId,
        LocalDate reservationDate,
        LocalTime startTime,
        LocalTime endTime,
        int totalHours,
        BigDecimal totalPay,
        BigDecimal remainingPayment

) {
}
