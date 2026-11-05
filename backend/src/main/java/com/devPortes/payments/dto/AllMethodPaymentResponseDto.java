package com.devPortes.payments.dto;

import java.util.List;

public record AllMethodPaymentResponseDto(
        List<MethodPaymentResponseDto> paymentMethod
) {
}
