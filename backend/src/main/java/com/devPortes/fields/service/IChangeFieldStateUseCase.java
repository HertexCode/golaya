package com.devPortes.fields.service;

import com.devPortes.fields.dto.FieldsCompleteResponseDto;

public interface IChangeFieldStateUseCase {
    FieldsCompleteResponseDto execute(Long id);
}
