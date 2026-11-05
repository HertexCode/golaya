package com.devPortes.fields.exceptions;

public class FieldNotFoundException extends RuntimeException {
    public FieldNotFoundException(Long id) {
        super("La cancha con id "+ id+" no esta registrada");
    }
}
