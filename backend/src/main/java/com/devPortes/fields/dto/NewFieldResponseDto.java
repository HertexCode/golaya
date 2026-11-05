package com.devPortes.fields.dto;

import java.util.List;

public record NewFieldResponseDto(

         Long id,
         Long locationId,
         String nameLocation,
         List<String> urlPictures,
         String name,
         String capacity,
         String sport,
         String surface,
         String description,
         List<String>details,
         String hourlyRate


) {
}
