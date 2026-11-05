package com.devPortes.payments.service;

import com.devPortes.payments.dto.AllMethodPaymentResponseDto;
import com.devPortes.payments.dto.MethodPaymentResponseDto;
import com.devPortes.payments.mapper.PaymentMethodMapper;
import com.devPortes.payments.repository.PaymentMethodJpaRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AllPaymentMethodUseCase implements IAllPaymentMethodUseCase {

    private final PaymentMethodJpaRepositoryAdapter repository;

    @Override
    public AllMethodPaymentResponseDto execute() {
        List<MethodPaymentResponseDto> methods = repository.findAll().stream()
                .map(PaymentMethodMapper::toDto)
                .toList();
        return new AllMethodPaymentResponseDto(methods);
    }
}
