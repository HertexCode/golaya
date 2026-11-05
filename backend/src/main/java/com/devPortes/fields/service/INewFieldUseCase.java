package com.devPortes.fields.service;

import com.devPortes.fields.dto.FieldsCompleteResponseDto;
import com.devPortes.fields.dto.NewFieldRequestDto;

public interface INewFieldUseCase {
    FieldsCompleteResponseDto execute (NewFieldRequestDto dto);
}
