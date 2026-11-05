package com.devPortes.location.service;

import com.devPortes.location.model.Location;
import com.devPortes.location.repository.LocationRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindLocationByIdUseCaseUseCase implements IFindLocationByIdUseCase {
    private final LocationRepositoryAdapter locationRepositoryAdapter;

    @Override
    public Optional<Location> execute(Long id) {
        return locationRepositoryAdapter.findById(id);
    }
}
