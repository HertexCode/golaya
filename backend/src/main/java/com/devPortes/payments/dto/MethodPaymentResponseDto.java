package com.devPortes.payments.dto;

public record MethodPaymentResponseDto (
        Long id,
        String paymentMethodName,
        String description,
        boolean state
){
}
