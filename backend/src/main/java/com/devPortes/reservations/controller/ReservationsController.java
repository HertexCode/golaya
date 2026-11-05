package com.devPortes.reservations.controller;

import com.devPortes.reservations.dto.CancelReservationResponseDto;
import com.devPortes.reservations.dto.ReservationSummaryResponseDto;
import com.devPortes.reservations.service.ICancelReservationUseCase;
import com.devPortes.reservations.service.IGetPendingReservationsUseCase;
import com.devPortes.reservations.service.IGetAllReservationsUseCase;
import com.devPortes.reservations.service.IGetReservationHistoryUseCase;
import com.devPortes.users.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/reservations")
public class ReservationsController {

    private final IGetPendingReservationsUseCase getPendingReservationsUseCase;
    private final IGetReservationHistoryUseCase getReservationHistoryUseCase;
    private final IGetAllReservationsUseCase getAllReservationsUseCase;
    private final ICancelReservationUseCase cancelReservationUseCase;

    @GetMapping("all")
    public ResponseEntity<List<ReservationSummaryResponseDto>> getAllReservations() {
        return ResponseEntity.ok(getAllReservationsUseCase.execute());
    }

    @GetMapping("pending")
    public ResponseEntity<List<ReservationSummaryResponseDto>> getPendingReservations(
            @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        Long userId = customUserDetails.getUser().getId();
        List<ReservationSummaryResponseDto> reservations = getPendingReservationsUseCase.execute(userId);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("history")
    public ResponseEntity<List<ReservationSummaryResponseDto>> getReservationHistory(
            @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        Long userId = customUserDetails.getUser().getId();
        List<ReservationSummaryResponseDto> reservations = getReservationHistoryUseCase.execute(userId);
        return ResponseEntity.ok(reservations);
    }

    @PatchMapping("{id}/cancel")
    public ResponseEntity<CancelReservationResponseDto> cancelReservation(
            @PathVariable Long id,
            @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        Long userId = customUserDetails.getUser().getId();
        CancelReservationResponseDto response = cancelReservationUseCase.execute(id, userId);
        return ResponseEntity.ok(response);
    }
}
