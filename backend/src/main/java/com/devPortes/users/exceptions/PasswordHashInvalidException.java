package com.devPortes.users.exceptions;

public class PasswordHashInvalidException extends RuntimeException {
    public PasswordHashInvalidException() {
        super("El sistema ha fallado al encriptar la contraseña");
    }
}
