package com.devPortes.reservations.service;

import com.devPortes.reservations.dto.AvailableDatesResponseDto;
import com.devPortes.reservations.mapper.ReservationInMapper;
import com.devPortes.reservations.model.Reservation;
import com.devPortes.reservations.repository.ReservationJpaRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AvailableDatesUseCase implements IAvailableDatesUseCase {

    private final ReservationJpaRepositoryAdapter repository;

    private static final List<LocalTime> POTENTIAL_HOURS = List.of(
            LocalTime.of(8, 0),
            LocalTime.of(9, 0),
            LocalTime.of(10, 0),
            LocalTime.of(16, 0),
            LocalTime.of(17, 0),
            LocalTime.of(18, 0),
            LocalTime.of(19, 0),
            LocalTime.of(20, 0),
            LocalTime.of(21, 0)
    );

    @Override
    public AvailableDatesResponseDto execute(Long fieldId, int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.atEndOfMonth();

        List<Reservation> reservations = repository.findByDateRange(fieldId, startDate, endDate);

        List<String> availableDates = new ArrayList<>();
        List<String> fullDates = new ArrayList<>();
        List<String> datesWithReservations = new ArrayList<>();
        LocalDateTime ahora = LocalDateTime.now(ZoneId.of("America/Bogota"));

        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            if (date.isBefore(LocalDate.now())) {
                continue;
            }

            final LocalDate currentDate = date;
            Set<LocalTime> bookedHours = reservations.stream()
                    .filter(r -> r.getReservationDate().equals(currentDate))
                    .flatMap(r -> {
                        java.util.List<LocalTime> hours = new java.util.ArrayList<>();
                        LocalTime start = r.getStartTime();
                        LocalTime end = r.getEndTime();
                        for (LocalTime h = start; h.isBefore(end); h = h.plusHours(1)) {
                            hours.add(h);
                        }
                        return hours.stream();
                    })
                    .collect(Collectors.toSet());

            if (!bookedHours.isEmpty()) {
                datesWithReservations.add(date.toString());
            }

            long availableCount = POTENTIAL_HOURS.stream()
                    .filter(hour -> !currentDate.equals(ahora.toLocalDate()) || hour.isAfter(ahora.toLocalTime()))
                    .filter(hour -> !bookedHours.contains(hour))
                    .count();

            if (availableCount > 0) {
                availableDates.add(date.toString());
            }

            boolean sinHorasDisponibles = POTENTIAL_HOURS.stream()
                    .filter(hour -> !currentDate.equals(ahora.toLocalDate()) || hour.isAfter(ahora.toLocalTime()))
                    .allMatch(bookedHours::contains);
            if (sinHorasDisponibles) {
                fullDates.add(date.toString());
            }
        }

        return ReservationInMapper.toAvailableDatesResponseDto(availableDates, fullDates, datesWithReservations);
    }
}
