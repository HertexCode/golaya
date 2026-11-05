package com.devPortes.fields.dto;

import com.devPortes.fields.model.FieldStateEnum;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

public record EditFieldRequestDto(
        Long locationId,
        List<String> UrlPictures,
        List<MultipartFile> pictures,
        String name,
        String capacity,
        String sport,
        String surface,
        String description,
        List<String> details,
        BigDecimal hourlyRate,
        FieldStateEnum state
) {
}
