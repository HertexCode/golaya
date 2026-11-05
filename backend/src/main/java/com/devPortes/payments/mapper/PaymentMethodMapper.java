package com.devPortes.payments.mapper;

import com.devPortes.payments.dto.MethodPaymentResponseDto;
import com.devPortes.payments.entities.PaymentMethodEntity;
import com.devPortes.payments.model.PaymentMethod;

public class PaymentMethodMapper {

    public static PaymentMethod toModel(PaymentMethodEntity entity) {
        return new PaymentMethod(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.isState()
        );
    }

    public static MethodPaymentResponseDto toDto(PaymentMethod model) {
        return new MethodPaymentResponseDto(
                model.getId(),
                model.getName(),
                model.getDescription(),
                model.isState()
        );
    }
}
