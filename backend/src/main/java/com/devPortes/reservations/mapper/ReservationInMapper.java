package com.devPortes.reservations.mapper;

import com.devPortes.fields.model.Field;
import com.devPortes.reservations.dto.AvailableDatesResponseDto;
import com.devPortes.reservations.dto.AvailableDatesTimesResponseDto;
import com.devPortes.reservations.dto.CompleteReservationResponseDto;
import com.devPortes.reservations.dto.NewReservationRequestDto;
import com.devPortes.reservations.dto.NewReservationResponseDto;
import com.devPortes.reservations.model.Reservation;
import com.devPortes.users.model.Client;

import java.time.LocalTime;
import java.util.List;

public class ReservationInMapper {

    public static Reservation toModel(Client user, Field field, NewReservationRequestDto dto) {
        return Reservation.create(
                user,
                field,
                dto.reservationDate(),
                dto.startTime(),
                dto.endTime(),
                dto.totalHours(),
                dto.totalPay(),
                dto.remainingPayment()
        );
    }

    public static NewReservationResponseDto toReservationResponse(Reservation model) {
        return new NewReservationResponseDto(
                model.getId()

        );
    }

    public static CompleteReservationResponseDto toCompleteReservationResponse(Reservation model) {
        return new CompleteReservationResponseDto(
                model.getId(),
                model.getUser().getId(),
                model.getField().getId(),
                model.getPayments().stream().map(p -> p.getId()).toList(),
                model.getReservationDate(),
                model.getStartTime(),
                model.getEndTime(),
                model.getTotalHours(),
                model.getTotalPay(),
                model.getRemainingPayment(),
                model.getState()
        );
    }

    public static AvailableDatesTimesResponseDto toAvailableDatesTimesResponseDto(List<LocalTime> hours) {
        return new AvailableDatesTimesResponseDto(hours);
    }

    public static AvailableDatesResponseDto toAvailableDatesResponseDto(List<String> availableDates, List<String> fullDates, List<String> datesWithReservations) {
        return new AvailableDatesResponseDto(availableDates, fullDates, datesWithReservations);
    }

}
