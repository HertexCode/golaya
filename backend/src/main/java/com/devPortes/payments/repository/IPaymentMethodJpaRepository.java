package com.devPortes.payments.repository;

import com.devPortes.payments.entities.PaymentMethodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPaymentMethodJpaRepository extends JpaRepository<PaymentMethodEntity, Long> {
}
