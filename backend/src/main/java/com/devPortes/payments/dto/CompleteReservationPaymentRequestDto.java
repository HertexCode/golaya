package com.devPortes.payments.dto;

import jakarta.validation.constraints.NotNull;

public record CompleteReservationPaymentRequestDto(
        @NotNull Long reservationId,
        @NotNull Long paymentMethodId,
        String notes
) {
}
