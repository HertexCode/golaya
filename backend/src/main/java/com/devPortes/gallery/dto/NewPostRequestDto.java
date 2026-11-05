package com.devPortes.gallery.dto;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public record NewPostRequestDto(
        String name,
        String description,
        List<MultipartFile> pictures,
        LocalDate eventDate
){
}
