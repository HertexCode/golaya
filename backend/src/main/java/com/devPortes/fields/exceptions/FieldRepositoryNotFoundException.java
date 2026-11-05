package com.devPortes.fields.exceptions;

public class FieldRepositoryNotFoundException extends RuntimeException{
     public FieldRepositoryNotFoundException(Long id) {
        super("Cancha " + id + " existía en application pero no al persistir");
    }

}
