package com.devPortes.location.service;

import com.devPortes.location.dto.ListLocationsResponseDto;
import com.devPortes.location.dto.LocationCompleteResponseDto;
import com.devPortes.location.mapper.LocationInMapper;
import com.devPortes.location.model.Location;
import com.devPortes.location.repository.LocationRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListLocationsUseCase implements IListLocationsUseCase{

    private final LocationRepositoryAdapter locationRepository;

    @Override
    public ListLocationsResponseDto execute() {
        List<Location> locations = locationRepository.findAll();
        List<LocationCompleteResponseDto> locationsResponse = LocationInMapper.toLocationsResponseListDto(locations);
        return new ListLocationsResponseDto(locationsResponse);
    }

}
