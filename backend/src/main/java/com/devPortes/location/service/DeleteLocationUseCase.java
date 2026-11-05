package com.devPortes.location.service;

import com.devPortes.location.exceptions.LocationNotFoundException;
import com.devPortes.location.repository.LocationRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteLocationUseCase {
    private final LocationRepositoryAdapter repository;

    public void execute(Long id) {
        if (repository.findById(id).isEmpty()) {
            throw new LocationNotFoundException(id);
        }
        repository.hide(id);
    }
}
