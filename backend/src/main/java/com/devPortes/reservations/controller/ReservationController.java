package com.devPortes.reservations.controller;

import com.devPortes.reservations.dto.AvailableDatesResponseDto;
import com.devPortes.reservations.dto.AvailableDatesTimesResponseDto;
import com.devPortes.reservations.dto.NewReservationRequestDto;
import com.devPortes.reservations.dto.NewReservationResponseDto;
import com.devPortes.reservations.service.IAvailableDatesTimesUseCase;
import com.devPortes.reservations.service.IAvailableDatesUseCase;
import com.devPortes.reservations.service.INewReservationUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/reservation")
public class ReservationController {
    private final INewReservationUseCase iNewReservationUseCase;
    private final IAvailableDatesTimesUseCase iAvailableDatesTimes;
    private final IAvailableDatesUseCase iAvailableDates;

    @PostMapping("new")
    public ResponseEntity<NewReservationResponseDto> newReservation(@Valid @RequestBody NewReservationRequestDto dto){
        NewReservationResponseDto response = iNewReservationUseCase.execute(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("available-dates-times/{fieldId}/{date}")
    public ResponseEntity<AvailableDatesTimesResponseDto> showAvailableDatesTimes(@PathVariable Long fieldId, @PathVariable LocalDate date) {
        AvailableDatesTimesResponseDto response = iAvailableDatesTimes.execute(fieldId, date);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("available-dates/{fieldId}/{year}/{month}")
    public ResponseEntity<AvailableDatesResponseDto> showAvailableDates(
            @PathVariable Long fieldId, @PathVariable int year, @PathVariable int month) {
        AvailableDatesResponseDto response = iAvailableDates.execute(fieldId, year, month);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
