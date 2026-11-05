package com.devPortes.payments.service;

import com.devPortes.payments.dto.CompleteReservationPaymentRequestDto;
import com.devPortes.payments.dto.CompleteReservationPaymentResponseDto;

public interface ICompleteReservationPaymentUseCase {
    CompleteReservationPaymentResponseDto execute(CompleteReservationPaymentRequestDto dto);
}
