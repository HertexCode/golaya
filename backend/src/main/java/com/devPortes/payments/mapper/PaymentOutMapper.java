package com.devPortes.payments.mapper;

import com.devPortes.payments.entities.PaymentEntity;
import com.devPortes.payments.model.Payment;
import com.devPortes.payments.model.PaymentMethod;

public class PaymentOutMapper {
    public static Payment toModel(PaymentEntity entity) {
        if (entity == null) return null;
        PaymentMethod paymentMethod = new PaymentMethod(
                entity.getPaymentMethod().getId(),
                entity.getPaymentMethod().getName(),
                entity.getPaymentMethod().getDescription(),
                entity.getPaymentMethod().isState()
        );
        return new Payment(
                entity.getId(),
                paymentMethod,
                null,
                entity.getTotalAmount(),
                entity.getPaymentDate(),
                null,
                entity.getNotes(),
                entity.getState()
        );
    }
}
