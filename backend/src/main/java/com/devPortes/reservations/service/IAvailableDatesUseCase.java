package com.devPortes.reservations.service;

import com.devPortes.reservations.dto.AvailableDatesResponseDto;

public interface IAvailableDatesUseCase {

    AvailableDatesResponseDto execute(Long fieldId, int year, int month);

}
