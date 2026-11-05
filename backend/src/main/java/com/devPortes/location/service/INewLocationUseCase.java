package com.devPortes.location.service;

import com.devPortes.location.dto.NewLocationRequestDto;
import com.devPortes.location.dto.NewLocationResponseDto;

public interface INewLocationUseCase {
    NewLocationResponseDto execute (NewLocationRequestDto dto);
}
