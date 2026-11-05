package com.devPortes.location.exceptions;

public class LocationNotFoundException extends RuntimeException {
    public LocationNotFoundException(Long id) {
        super("La Ubicacion con id: "+id+" no esta registrada");
    }
}
