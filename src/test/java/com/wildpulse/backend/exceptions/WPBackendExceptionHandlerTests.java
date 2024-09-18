package com.wildpulse.backend.exceptions;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.wildpulse.commons.exceptions.enums.WPErrorCode;
import com.wildpulse.commons.models.errors.WPErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

@ExtendWith(MockitoExtension.class)
public class WPBackendExceptionHandlerTests {

    @InjectMocks private WPBackendExceptionHandler wpExceptionHandler;

    @Test
    public void testHandleDataIntegrityViolationException() {
        DataIntegrityViolationException exception =
                new DataIntegrityViolationException("Duplicate entry");
        WPErrorResponse response =
                wpExceptionHandler.handleDataIntegrityViolationException(exception);
        assertNotNull(response);
        assertEquals(1, response.getErrors().size());
        assertEquals(WPErrorCode.WP_DB_DUP_ERROR, response.getErrors().getFirst().getErrorCode());
        assertEquals("Duplicate entry", response.getErrors().getFirst().getMessage());
    }

    @Test
    public void testHandleEntityNotFoundException() {
        EntityNotFoundException exception = new EntityNotFoundException("Entity not found");
        WPErrorResponse response = wpExceptionHandler.handleEntityNotFoundException(exception);
        assertNotNull(response);
        assertEquals(1, response.getErrors().size());
        assertEquals(
                WPErrorCode.WP_REC_NOT_FOUND_ERROR, response.getErrors().getFirst().getErrorCode());
        assertEquals("Entity not found", response.getErrors().getFirst().getMessage());
    }

    @Test
    public void testHandleMethodArgumentNotValidException() {
        FieldError fieldError = new FieldError("objectName", "field", "must not be null");
        MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);
        when(exception.getAllErrors()).thenReturn(List.of(fieldError));
        WPErrorResponse response =
                wpExceptionHandler.handleMethodArgumentNotValidException(exception);
        assertNotNull(response);
        assertEquals(1, response.getErrors().size());
        assertEquals(
                WPErrorCode.WP_VALIDATION_ERROR, response.getErrors().getFirst().getErrorCode());
        assertEquals("must not be null", response.getErrors().getFirst().getMessage());
    }
}
