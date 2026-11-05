package com.devPortes.reservations.exceptions;

import com.devPortes.configuration.BaseExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ReservationGlobalExceptionHandler extends BaseExceptionHandler {
    @ExceptionHandler(InvalidReservationScheduleException.class)
    public ResponseEntity<ErrorDetails> handleInvalidSchedule(InvalidReservationScheduleException exception) {
        return buildResponse(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ReservationScheduleConflictException.class)
    public ResponseEntity<ErrorDetails> handleScheduleConflict(ReservationScheduleConflictException exception) {
        return buildResponse(exception, HttpStatus.CONFLICT);
    }
}
