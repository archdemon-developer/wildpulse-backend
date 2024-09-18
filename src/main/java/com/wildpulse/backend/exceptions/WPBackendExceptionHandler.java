package com.wildpulse.backend.exceptions;

import com.wildpulse.commons.exceptions.WPExceptionHandler;
import com.wildpulse.commons.exceptions.enums.WPErrorCode;
import com.wildpulse.commons.models.errors.WPErrorDetails;
import com.wildpulse.commons.models.errors.WPErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class WPBackendExceptionHandler extends WPExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public WPErrorResponse handleDataIntegrityViolationException(
            DataIntegrityViolationException dataIntegrityViolationException) {
        List<WPErrorDetails> errors = new ArrayList<>();
        WPErrorDetails errorDetails =
                new WPErrorDetails(
                        WPErrorCode.WP_DB_DUP_ERROR, dataIntegrityViolationException.getMessage());
        errors.add(errorDetails);
        return new WPErrorResponse(errors);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public WPErrorResponse handleEntityNotFoundException(
            EntityNotFoundException entityNotFoundException) {
        List<WPErrorDetails> errors = new ArrayList<>();
        WPErrorDetails errorDetails =
                new WPErrorDetails(
                        WPErrorCode.WP_REC_NOT_FOUND_ERROR, entityNotFoundException.getMessage());
        errors.add(errorDetails);
        return new WPErrorResponse(errors);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public WPErrorResponse handleMethodArgumentNotValidException(
            MethodArgumentNotValidException methodArgumentNotValidException) {
        List<WPErrorDetails> errors =
                methodArgumentNotValidException.getAllErrors().stream()
                        .map(
                                objectError ->
                                        new WPErrorDetails(
                                                WPErrorCode.WP_VALIDATION_ERROR,
                                                objectError.getDefaultMessage()))
                        .toList();
        return new WPErrorResponse(errors);
    }
}
