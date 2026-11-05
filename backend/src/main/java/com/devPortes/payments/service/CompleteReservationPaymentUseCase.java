package com.devPortes.payments.service;

import com.devPortes.payments.dto.CompleteReservationPaymentRequestDto;
import com.devPortes.payments.dto.CompleteReservationPaymentResponseDto;
import com.devPortes.payments.entities.PaymentEntity;
import com.devPortes.payments.entities.PaymentMethodEntity;
import com.devPortes.payments.model.PaymentStateEnum;
import com.devPortes.payments.repository.IPaymentJpaRepository;
import com.devPortes.reservations.entities.ReservationEntity;
import com.devPortes.reservations.model.EstadoReservationEnum;
import com.devPortes.reservations.repository.IReservationJpaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CompleteReservationPaymentUseCase implements ICompleteReservationPaymentUseCase {
    private final IPaymentJpaRepository paymentRepository;
    private final IReservationJpaRepository reservationRepository;
    private final com.devPortes.payments.repository.IPaymentMethodJpaRepository paymentMethodRepository;

    @Override
    @Transactional
    public CompleteReservationPaymentResponseDto execute(CompleteReservationPaymentRequestDto dto) {
        ReservationEntity reservation = reservationRepository.findById(dto.reservationId())
                .orElseThrow(() -> new IllegalArgumentException("Reserva no encontrada: " + dto.reservationId()));

        if (reservation.getState() == EstadoReservationEnum.CANCELADA) {
            throw new IllegalStateException("No se puede cobrar una reserva cancelada");
        }
        if (reservation.getRemainingPayment().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalStateException("La reserva ya no tiene saldo pendiente");
        }

        PaymentMethodEntity paymentMethod = paymentMethodRepository.findById(dto.paymentMethodId())
                .orElseThrow(() -> new IllegalArgumentException("Método de pago no encontrado: " + dto.paymentMethodId()));

        BigDecimal amount = reservation.getRemainingPayment();
        PaymentEntity payment = new PaymentEntity();
        payment.setReservation(reservation);
        payment.setPaymentMethod(paymentMethod);
        payment.setTotalAmount(amount);
        payment.setPaymentDate(LocalDateTime.now());
        payment.setFixedPercentage(0);
        payment.setState(PaymentStateEnum.PAYMENT);
        payment.setNotes(dto.notes() == null || dto.notes().isBlank() ? "Pago final registrado por administración" : dto.notes());
        PaymentEntity savedPayment = paymentRepository.save(payment);

        reservation.setRemainingPayment(BigDecimal.ZERO);
        reservation.setState(EstadoReservationEnum.COMPLETADA);
        reservationRepository.save(reservation);

        return new CompleteReservationPaymentResponseDto(
                savedPayment.getId(),
                reservation.getId(),
                amount,
                reservation.getRemainingPayment(),
                reservation.getState().name()
        );
    }
}
