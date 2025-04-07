package com.suman.springsecurity.exception;

public class DateTimeParsingException extends RuntimeException {
    public DateTimeParsingException(String message) {
        super(message);
    }

    public DateTimeParsingException(String message, Throwable cause) {
        super(message, cause);
    }

    public DateTimeParsingException(Throwable cause) {
        super(cause);
    }
}
