package com.devPortes.reservations.entities;

import com.devPortes.fields.entities.FieldEntity;
import com.devPortes.payments.entities.PaymentEntity;
import com.devPortes.reservations.model.EstadoReservationEnum;
import com.devPortes.users.entities.ClientEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reservations")
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class ReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private ClientEntity clientEntity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "field_id", nullable = false)
    private FieldEntity fieldEntity;

    @Column(name = "reservation_date", nullable = false)
    private LocalDate reservationDate;

    @OneToMany(mappedBy = "reservation", fetch = FetchType.LAZY)
    private List<PaymentEntity> payments = new ArrayList<>();

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @Column(name = "total_hours", nullable = false)
    private int totalHours;

    @Column(name = "total_pay", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalPay;

    @Column(name = "remaining_payment", nullable = false, precision = 10, scale = 2)
    private BigDecimal remainingPayment;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoReservationEnum state;
}
