package com.devPortes.reservations.service;

import com.devPortes.reservations.dto.CancelReservationResponseDto;

public interface ICancelReservationUseCase {

    CancelReservationResponseDto execute(Long reservationId, Long userId);

}
