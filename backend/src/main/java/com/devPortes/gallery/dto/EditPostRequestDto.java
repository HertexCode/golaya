package com.devPortes.gallery.dto;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public record EditPostRequestDto(
    List<String> urlPictures,
    List<MultipartFile> pictures,
    String name,
    String description,
    LocalDate eventDate
) {
}
