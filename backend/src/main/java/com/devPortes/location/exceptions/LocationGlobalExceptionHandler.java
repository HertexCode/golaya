package com.devPortes.location.exceptions;

import com.devPortes.configuration.BaseExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class LocationGlobalExceptionHandler extends BaseExceptionHandler {

    @ExceptionHandler(ExistingLocationDataBaseException.class)
    public ResponseEntity<ErrorDetails> handleFindDataBaseExistingException(RuntimeException exception) {
        return buildResponse(exception, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({LocationNotFoundException.class,
    LocationRepositoryNotFoundException.class})
    public ResponseEntity<ErrorDetails> handleFindDataBaseNotFoundException(RuntimeException exception) {
        return buildResponse(exception, HttpStatus.NOT_FOUND);
    }

}
