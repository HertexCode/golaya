package com.devPortes.location.service;

import com.devPortes.location.dto.LocationCompleteResponseDto;
import com.devPortes.location.dto.UpdateLocationRequestDto;

public interface IUpdateLocationUseCase {
    LocationCompleteResponseDto execute(Long id, UpdateLocationRequestDto dto);
}