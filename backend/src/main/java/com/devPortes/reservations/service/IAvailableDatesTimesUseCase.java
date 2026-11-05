package com.devPortes.reservations.service;

import com.devPortes.reservations.dto.AvailableDatesTimesResponseDto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface IAvailableDatesTimesUseCase {

    AvailableDatesTimesResponseDto execute(Long fieldId, LocalDate date);

}
