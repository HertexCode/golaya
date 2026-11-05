package com.devPortes.payments.entities;

import com.devPortes.payments.model.PaymentStateEnum;
import com.devPortes.reservations.entities.ReservationEntity;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "pagos")
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "payment_method_id", nullable = false)
    private PaymentMethodEntity paymentMethod;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "reservation_id", nullable = false)
    private ReservationEntity reservation;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;
    @Column(name = "payment_date")
    private LocalDateTime paymentDate;
    @Column(name = "fixed_percentage")
    private double fixedPercentage;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStateEnum state;
    @Column(nullable = false)
    private String notes;

}
