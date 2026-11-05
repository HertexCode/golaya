package com.devPortes.location.exceptions;

public class ExistingLocationDataBaseException extends RuntimeException {
    public ExistingLocationDataBaseException(String name) {
        super("La ubicacion "+name+" ya se encuentra registrada");
    }
}
