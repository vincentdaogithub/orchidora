package com.orchidora.be.infrastructure.error;

import com.orchidora.be.application.exception.OrchidoraException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class OrchidoraExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<OrchidoraErrorResponse> handleCustomException(OrchidoraException exception) {
        final OrchidoraErrorResponse response = OrchidoraErrorResponse.builder()
                .target(exception.getTarget())
                .message(exception.getMessage())
                .build();
        return ResponseEntity.status(exception.getStatus()).body(response);
    }

    @ExceptionHandler
    public ResponseEntity<OrchidoraErrorResponse> handleUnknownException(Exception exception) {
        final OrchidoraErrorResponse response = OrchidoraErrorResponse.builder()
                .target("server")
                .message(exception.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler({UsernameNotFoundException.class, AuthenticationException.class})
    public ResponseEntity<OrchidoraErrorResponse> handleAuthException(Exception exception) {
        final OrchidoraErrorResponse response = OrchidoraErrorResponse.builder()
                .target("auth")
                .message(exception.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler
    public ResponseEntity<OrchidoraErrorResponse> handleValidationException(MethodArgumentNotValidException exception) {
        final FieldError firstFieldError = exception.getFieldError();
        final OrchidoraErrorResponse response = OrchidoraErrorResponse.builder()
                .target(Objects.requireNonNull(firstFieldError).getField())
                .message(firstFieldError.getDefaultMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
