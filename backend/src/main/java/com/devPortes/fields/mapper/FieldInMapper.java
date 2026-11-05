package com.devPortes.fields.mapper;

import com.devPortes.fields.dto.EditFieldRequestDto;
import com.devPortes.fields.dto.FieldsCompleteResponseDto;
import com.devPortes.fields.dto.NewFieldRequestDto;
import com.devPortes.fields.model.Field;
import com.devPortes.fields.model.FieldStateEnum;
import com.devPortes.location.model.Location;

import java.math.BigDecimal;
import java.util.List;

public class FieldInMapper {
    public static Field toModel(NewFieldRequestDto dto, Location location, List<String> urlPictures){
        return Field.create(
                location,
                urlPictures,
                dto.name(),
                dto.capacity(),
                dto.sport(),
                dto.surface(),
                dto.description(),
                dto.details(),
                dto.hourlyRate()
        );
    }
    public static Field toEditModel (EditFieldRequestDto dto,Location location){
        return Field.edit(
                location,
                dto.UrlPictures(),
                dto.name(),
                dto.capacity(),
                dto.sport(),
                dto.surface(),
                dto.description(),
                dto.details(),
                dto.hourlyRate(),
                dto.state()
        );

    }
    public static FieldsCompleteResponseDto toFieldsCompleteResponseDto (Field model){
       return new FieldsCompleteResponseDto(
               model.getId(),
               model.getLocation().getId(),
               model.getLocation().getName(),
               model.getLocation().getAddress(),
               model.getLocation().getUrlQrAddress(),
               model.getLocation().getUrlAddress(),
               model.getUrlPictures(),
               model.getName(),
               model.getCapacity(),
               model.getSport(),
               model.getSurface(),
               model.getDescription(),
               model.getDetails(),
               model.getHourlyRate(),
               model.getState(),
               model.isVisible()
       );

    }

    public static List<FieldsCompleteResponseDto> toFieldsCompleteResponseDtoList(List<Field> models){
        return models.stream().map(FieldInMapper::toFieldsCompleteResponseDto)
                .toList();
    }
}
