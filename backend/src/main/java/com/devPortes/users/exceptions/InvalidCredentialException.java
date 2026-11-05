package com.devPortes.users.exceptions;

public class InvalidCredentialException extends RuntimeException {
    public InvalidCredentialException() {
        super("Credenciales invalidas");
    }
}
