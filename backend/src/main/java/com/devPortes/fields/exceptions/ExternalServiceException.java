package com.devPortes.fields.exceptions;

public class ExternalServiceException extends RuntimeException {
    public ExternalServiceException(String serviceName, String message) {
        super("Error en el servicio: "+serviceName+ " ha indicado que: "+ message );
    }
}
