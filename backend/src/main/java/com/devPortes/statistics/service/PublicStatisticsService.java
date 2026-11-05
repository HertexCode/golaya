package com.devPortes.statistics.service;

import com.devPortes.fields.model.FieldStateEnum;
import com.devPortes.fields.repository.IFieldJpaRepository;
import com.devPortes.statistics.dto.PublicStatisticsResponseDto;
import com.devPortes.location.repository.ILocationJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PublicStatisticsService {
    private final IFieldJpaRepository fieldRepository;
    private final ILocationJpaRepository locationRepository;

    public PublicStatisticsResponseDto execute() {
        var fields = fieldRepository.findAll();
        long availableFields = fields.stream()
                .filter(field -> field.getState() == FieldStateEnum.DISPONIBLE)
                .count();
        long sports = fields.stream()
                .filter(field -> field.getState() == FieldStateEnum.DISPONIBLE)
                .map(field -> field.getSport().trim().toLowerCase(Locale.ROOT))
                .filter(sport -> !sport.isBlank())
                .collect(Collectors.toSet())
                .size();

        return new PublicStatisticsResponseDto(
                availableFields,
                sports,
                locationRepository.findAllByVisibleTrue().size(),
                true
        );
    }
}
