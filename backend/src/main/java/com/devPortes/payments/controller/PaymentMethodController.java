package com.devPortes.payments.controller;

import com.devPortes.payments.dto.AllMethodPaymentResponseDto;
import com.devPortes.payments.service.IAllPaymentMethodUseCase;
import com.devPortes.payments.dto.CompleteReservationPaymentRequestDto;
import com.devPortes.payments.dto.CompleteReservationPaymentResponseDto;
import com.devPortes.payments.service.ICompleteReservationPaymentUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/payment-method")
public class PaymentMethodController {

    private final IAllPaymentMethodUseCase iAllPaymentMethodUseCase;
    private final ICompleteReservationPaymentUseCase completeReservationPaymentUseCase;

    @GetMapping("all")
    public ResponseEntity<AllMethodPaymentResponseDto> showAllMethodPayment (){
        AllMethodPaymentResponseDto response = iAllPaymentMethodUseCase.execute();
    return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("reservations/final-payment")
    public ResponseEntity<CompleteReservationPaymentResponseDto> completeReservationPayment(
            @Valid @RequestBody CompleteReservationPaymentRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(completeReservationPaymentUseCase.execute(dto));
    }
}
