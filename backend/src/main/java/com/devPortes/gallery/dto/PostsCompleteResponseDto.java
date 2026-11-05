package com.devPortes.gallery.dto;

import java.time.LocalDate;
import java.util.List;

public record PostsCompleteResponseDto(
        Long id,
        String name,
        String description,
        List<String> urlPictures,
        LocalDate eventDate
) {
}
