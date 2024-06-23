package com.orchidora.be.application.exception;

@SuppressWarnings("unused")
public class OrchidoraException extends RuntimeException {

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
