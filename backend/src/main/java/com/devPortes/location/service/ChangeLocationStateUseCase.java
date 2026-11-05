package com.devPortes.location.service;

import com.devPortes.location.dto.LocationCompleteResponseDto;
import com.devPortes.location.exceptions.LocationNotFoundException;
import com.devPortes.location.mapper.LocationInMapper;
import com.devPortes.location.model.Location;
import com.devPortes.location.repository.LocationRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChangeLocationStateUseCase implements IChangeLocationStateUseCase {
    private final LocationRepositoryAdapter locationRepositoryAdapter;

    @Override
    public LocationCompleteResponseDto execute(Long id) {
        Location existingLocation = locationRepositoryAdapter.findById(id)
                .orElseThrow(() -> new LocationNotFoundException(id));

        Location toggledLocation = locationRepositoryAdapter.changeState(id, existingLocation.isState());
        return LocationInMapper.toLocationCompleteDto(toggledLocation);
    }
}
