package com.devPortes.location.service;

import com.devPortes.location.model.Location;

import java.util.Optional;

public interface IFindLocationByIdUseCase {
    Optional<Location> execute (Long id);
}
