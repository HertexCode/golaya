package com.devPortes.statistics.service;

import com.devPortes.reservations.model.EstadoReservationEnum;
import com.devPortes.reservations.model.Reservation;
import com.devPortes.reservations.repository.ReservationJpaRepositoryAdapter;
import com.devPortes.statistics.dto.UserStatisticsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserStatisticsService {
    private final ReservationJpaRepositoryAdapter reservationRepository;

    public UserStatisticsResponseDto execute(Long userId) {
        var reservations = reservationRepository.findAllByUserId(userId);
        var completed = reservations.stream()
                .filter(reservation -> reservation.getState() == EstadoReservationEnum.COMPLETADA)
                .toList();

        long playedHours = completed.stream()
                .mapToLong(Reservation::getTotalHours)
                .sum();

        Map<String, Long> hoursByField = completed.stream()
                .collect(Collectors.groupingBy(
                        reservation -> reservation.getField().getName(),
                        Collectors.summingLong(Reservation::getTotalHours)
                ));

        var favorite = hoursByField.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElse(null);

        return new UserStatisticsResponseDto(
                reservations.stream()
                        .filter(reservation -> reservation.getState() != EstadoReservationEnum.CANCELADA)
                        .count(),
                playedHours,
                favorite == null ? "N/A" : favorite.getKey(),
                favorite == null ? 0 : favorite.getValue()
        );
    }
}
