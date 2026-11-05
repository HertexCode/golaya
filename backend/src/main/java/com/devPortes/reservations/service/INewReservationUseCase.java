package com.devPortes.reservations.service;

import com.devPortes.reservations.dto.NewReservationRequestDto;
import com.devPortes.reservations.dto.NewReservationResponseDto;

public interface INewReservationUseCase {

    NewReservationResponseDto execute(NewReservationRequestDto dto);

}
