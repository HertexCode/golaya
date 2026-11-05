package com.devPortes.fields.exceptions;

import com.devPortes.configuration.BaseExceptionHandler;
import com.devPortes.users.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@ControllerAdvice
public class FieldGlobalExceptionHandler extends BaseExceptionHandler {

    @ExceptionHandler({ExternalServiceException.class})
    public ResponseEntity<ErrorDetails> handleExternalServiceException(RuntimeException ex) {
        return buildResponse(ex, HttpStatus.BAD_GATEWAY);
    }
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ErrorDetails> handleMaxUploadSize(MaxUploadSizeExceededException exception) {
        return buildResponse(exception, HttpStatus.PAYLOAD_TOO_LARGE); // 413
    }
    @ExceptionHandler({FieldRepositoryNotFoundException.class,FieldNotFoundException.class})
    public ResponseEntity<ErrorDetails> handleFindDataBaseNotFoundException(RuntimeException exception) {
        return buildResponse(exception, HttpStatus.NOT_FOUND);
    }
}
