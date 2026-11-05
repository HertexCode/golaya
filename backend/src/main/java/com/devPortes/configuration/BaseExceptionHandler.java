package com.devPortes.configuration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public abstract class BaseExceptionHandler {
    public record ErrorDetails(int status, String message, String field) {
        public ErrorDetails(int status, String message) {
            this(status, message, null);
        }
    }
    public record ValidationErrorDetails(int status, List<String> messages) {}

    protected ResponseEntity<ErrorDetails> buildResponse(Exception exception, HttpStatus status) {
        return new ResponseEntity<>(new ErrorDetails(status.value(), exception.getMessage()), status);
    }

    protected ResponseEntity<ValidationErrorDetails> buildValidationResponse(List<String> errors, HttpStatus status) {
        return new ResponseEntity<>(new ValidationErrorDetails(status.value(), errors), status);
    }
}
