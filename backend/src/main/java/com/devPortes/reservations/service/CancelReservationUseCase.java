package com.devPortes.reservations.service;

import com.devPortes.reservations.dto.CancelReservationResponseDto;
import com.devPortes.reservations.model.Reservation;
import com.devPortes.reservations.repository.ReservationJpaRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CancelReservationUseCase implements ICancelReservationUseCase {

    private final ReservationJpaRepositoryAdapter repository;

    @Override
    public CancelReservationResponseDto execute(Long reservationId, Long userId) {
        Reservation reservation = repository.cancelByIdAndUserId(reservationId, userId);
        return new CancelReservationResponseDto(
                reservation.getId(),
                reservation.getState().name()
        );
    }
}
