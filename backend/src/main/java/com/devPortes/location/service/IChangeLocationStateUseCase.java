package com.devPortes.location.service;

import com.devPortes.location.dto.LocationCompleteResponseDto;

public interface IChangeLocationStateUseCase {
    LocationCompleteResponseDto execute(Long id);
}