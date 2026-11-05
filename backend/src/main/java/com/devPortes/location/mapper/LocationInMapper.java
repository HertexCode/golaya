package com.devPortes.location.mapper;

import com.devPortes.location.dto.NewLocationRequestDto;
import com.devPortes.location.dto.LocationCompleteResponseDto;
import com.devPortes.location.dto.NewLocationResponseDto;
import com.devPortes.location.dto.UpdateLocationRequestDto;
import com.devPortes.location.model.Location;

import java.util.List;

public class LocationInMapper {

    public static Location toModel (String urlQr,NewLocationRequestDto dto){
        return Location.create(
                dto.name(),
                dto.headquarters(),
                dto.address(),
                urlQr,
                dto.urlQrAddress(),
                dto.description()
        );
    }
    public static Location toEditModel(UpdateLocationRequestDto dto){
        return Location.edit(
                dto.name(),
                dto.headquarters(),
                dto.address(),
                dto.urlQrAddress(),
                dto.urlQrAddress(),
                dto.description()
        );
    }
    public static NewLocationResponseDto toNewLocationDto(Location model){
        return new NewLocationResponseDto(
                model.getId()
        );
    }
    public static LocationCompleteResponseDto toLocationCompleteDto(Location model){
        return new LocationCompleteResponseDto(
                model.getId(),
                model.getName(),
                model.getHeadquarters(),
                model.getAddress(),
                model.getUrlQrAddress(),
                model.getUrlAddress(),
                model.getDescription(),
                model.isState()
        );
    }

    public static List<LocationCompleteResponseDto> toLocationsResponseListDto(List<Location> models){
        return models.stream().map(LocationInMapper::toLocationCompleteDto)
                .toList();
    }
}
