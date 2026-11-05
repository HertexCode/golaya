package com.devPortes.payments.dto;

import java.math.BigDecimal;

public record CompleteReservationPaymentResponseDto(
        Long paymentId,
        Long reservationId,
        BigDecimal amount,
        BigDecimal remainingPayment,
        String reservationStatus
) {
}
