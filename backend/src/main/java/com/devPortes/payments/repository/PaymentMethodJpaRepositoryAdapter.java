package com.devPortes.payments.repository;

import com.devPortes.payments.mapper.PaymentMethodMapper;
import com.devPortes.payments.model.PaymentMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PaymentMethodJpaRepositoryAdapter {

    private final IPaymentMethodJpaRepository jpa;

    public List<PaymentMethod> findAll() {
        return jpa.findAll().stream()
                .map(PaymentMethodMapper::toModel)
                .toList();
    }
}
