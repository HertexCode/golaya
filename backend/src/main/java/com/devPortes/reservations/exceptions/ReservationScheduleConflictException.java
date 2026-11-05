package com.devPortes.reservations.exceptions;

public class ReservationScheduleConflictException extends RuntimeException {
    public ReservationScheduleConflictException() {
        super("La cancha ya está reservada en el horario seleccionado.");
    }
}
