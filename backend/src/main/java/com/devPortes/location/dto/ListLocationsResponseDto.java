package com.devPortes.location.dto;

import com.devPortes.location.model.Location;

import java.util.List;

public record ListLocationsResponseDto(
        List<LocationCompleteResponseDto> locations) {
}
