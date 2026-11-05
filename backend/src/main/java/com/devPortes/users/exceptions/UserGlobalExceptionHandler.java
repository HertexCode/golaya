package com.devPortes.users.exceptions;

import com.devPortes.configuration.BaseExceptionHandler;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class UserGlobalExceptionHandler extends BaseExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleFindDataBaseNotFoundException(RuntimeException exception) {
        return buildResponse(exception, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(InvalidCredentialException.class)
    public ResponseEntity<ErrorDetails> handleCredentialException(RuntimeException exception) {
        return buildResponse(exception, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorDetails> handleValidation(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();
        List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
        List<String> errors = fieldErrorList.stream().map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();
        return buildValidationResponse(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PasswordHashInvalidException.class)
    public ResponseEntity<ErrorDetails> handleSystemErrorException(RuntimeException exception) {
        return buildResponse(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({IdentityDocumentInvalidException.class, EmailInvalidException.class,})
    public ResponseEntity<ErrorDetails> handleInvalidDataException(RuntimeException exception) {
        return buildResponse(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExistingUserDataBaseException.class)
    public ResponseEntity<ErrorDetails> handleFindDataBaseExistingException(ExistingUserDataBaseException exception) {
        return new ResponseEntity<>(
                new ErrorDetails(HttpStatus.CONFLICT.value(), exception.getMessage(), exception.getField()),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorDetails> handleDataIntegrityViolation(DataIntegrityViolationException exception) {
        return buildResponse(new Exception("Ya existe un registro con esos datos."), HttpStatus.CONFLICT);
    }

}
