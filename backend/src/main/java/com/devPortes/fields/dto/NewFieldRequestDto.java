package com.devPortes.fields.dto;

import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

public record NewFieldRequestDto(

        Long locationId,
        List<MultipartFile> pictures,
        String name,
        String capacity,
        String sport,
        String surface,
        String description,
        List<String> details,
        BigDecimal hourlyRate

) {
}
