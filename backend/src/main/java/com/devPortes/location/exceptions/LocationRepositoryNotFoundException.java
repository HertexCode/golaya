package com.devPortes.location.exceptions;

public class LocationRepositoryNotFoundException extends RuntimeException {
    public LocationRepositoryNotFoundException(Long id) {
        super("Ubicacion " + id + " existía en application pero no al persistir");
    }
}
