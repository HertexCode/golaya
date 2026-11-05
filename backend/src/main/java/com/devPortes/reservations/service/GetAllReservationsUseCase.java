package com.devPortes.reservations.service;

import com.devPortes.reservations.dto.ReservationSummaryResponseDto;
import com.devPortes.reservations.model.Reservation;
import com.devPortes.reservations.repository.ReservationJpaRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllReservationsUseCase implements IGetAllReservationsUseCase {
    private final ReservationJpaRepositoryAdapter repository;

    @Override
    public List<ReservationSummaryResponseDto> execute() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    private ReservationSummaryResponseDto toResponse(Reservation reservation) {
        return new ReservationSummaryResponseDto(
                reservation.getId(),
                reservation.getField().getName(),
                reservation.getReservationDate().toString(),
                reservation.getStartTime().toString(),
                "Individual",
                reservation.getState().name(),
                reservation.getEndTime().toString(),
                reservation.getTotalHours(),
                reservation.getTotalPay(),
                reservation.getRemainingPayment(),
                reservation.getUser().getName(),
                reservation.getUser().getEmail(),
                reservation.getField().getLocation().getName(),
                reservation.getField().getLocation().getAddress(),
                reservation.getField().getLocation().getUrlQrAddress()
                ,reservation.getField().getLocation().getUrlAddress()
        );
    }
}
