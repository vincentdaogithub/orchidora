package com.orchidora.be.application.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@SuppressWarnings("unused")
public class OrchidoraException extends RuntimeException {

    private HttpStatus status;
    private String target;

    public OrchidoraException(HttpStatus status, String target, String message) {
        super(message);
        this.target = target;
    }

    public OrchidoraException() {
    }

    public OrchidoraException(String message) {
        super(message);
    }

    public OrchidoraException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrchidoraException(Throwable cause) {
        super(cause);
    }

    public OrchidoraException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
