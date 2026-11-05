package com.devPortes.fields.service;

import com.devPortes.fields.dto.EditFieldRequestDto;
import com.devPortes.fields.dto.FieldsCompleteResponseDto;
import com.devPortes.fields.dto.NewFieldRequestDto;

public interface IEditFieldUseCase {
    FieldsCompleteResponseDto execute(Long id, EditFieldRequestDto dto);
}
