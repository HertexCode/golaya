package com.devPortes.users.exceptions;

public class ExistingUserDataBaseException extends RuntimeException {
    private final String field;

    public ExistingUserDataBaseException(String email) {
        super("El usuario " + email + " ya se encuentra registrado.");
        this.field = "email";
    }

    public ExistingUserDataBaseException(String field, String message) {
        super(message);
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
