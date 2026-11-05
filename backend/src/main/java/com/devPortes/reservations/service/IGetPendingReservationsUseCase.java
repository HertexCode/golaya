package com.devPortes.reservations.service;

import com.devPortes.reservations.dto.ReservationSummaryResponseDto;

import java.util.List;

public interface IGetPendingReservationsUseCase {

    List<ReservationSummaryResponseDto> execute(Long userId);

}
