package com.devPortes.reservations.exceptions;

public class InvalidReservationScheduleException extends RuntimeException {
    public InvalidReservationScheduleException() {
        super("No se pueden reservar horarios pasados.");
    }
}
