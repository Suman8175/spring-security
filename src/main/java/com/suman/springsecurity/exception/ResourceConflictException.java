package com.suman.springsecurity.exception;

public class ResourceConflictException extends RuntimeException{
    public ResourceConflictException() {
    }

    public ResourceConflictException(String message) {
        super(message);
    }

    public ResourceConflictException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceConflictException(Throwable cause) {
        super(cause);
    }
}
