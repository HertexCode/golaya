package com.devPortes.users.exceptions;

public class IdentityDocumentInvalidException extends RuntimeException {
    public IdentityDocumentInvalidException(String identityDocument) {
        super("El numero de documento "+identityDocument+" no cumple con el formato valido, debe contener menos de 10 digitos");
    }
}