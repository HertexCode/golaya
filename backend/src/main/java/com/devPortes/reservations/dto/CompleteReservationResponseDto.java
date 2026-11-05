package com.devPortes.reservations.dto;

import com.devPortes.reservations.model.EstadoReservationEnum;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record CompleteReservationResponseDto(
        Long id,
        Long userId,
        Long fieldId,
        List<Long> paymentIds,
        LocalDate reservationDate,
        LocalTime startTime,
        LocalTime endTime,
        int totalHours,
        BigDecimal totalPay,
        BigDecimal remainingPayment,
        EstadoReservationEnum state
) {
}
