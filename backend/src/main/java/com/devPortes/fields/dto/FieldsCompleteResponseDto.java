package com.devPortes.fields.dto;

import com.devPortes.fields.model.FieldStateEnum;
import com.devPortes.location.model.Location;

import java.math.BigDecimal;
import java.util.List;

public record FieldsCompleteResponseDto(

        Long id,
        Long locationId,
        String locationName,
        String locationAddress,
        String locationQrUrl,
        String locationUrl,
        List<String> urlPictures,
        String name,
        String capacity,
        String sport,
        String surface,
        String description,
        List<String> details,
        BigDecimal hourlyRate,
        FieldStateEnum state,
        boolean visible

) {
}
