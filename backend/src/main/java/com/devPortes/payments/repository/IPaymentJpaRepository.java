package com.devPortes.payments.repository;

import com.devPortes.payments.entities.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPaymentJpaRepository extends JpaRepository<PaymentEntity, Long> {
}
