package com.suman.springsecurity.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        Map<String,String > errorMap =new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error->{
            log.error("Error : {} Field cannot be empty. {}", error.getField(), error.getDefaultMessage());
            errorMap.put(error.getField(),error.getDefaultMessage());
        });
        CustomErrorMessage<String> customErrorMessage = new CustomErrorMessage<>();
        customErrorMessage.setStatus("Error");
        customErrorMessage.setTimestamp(System.currentTimeMillis());
        customErrorMessage.setMessage(String.join(",",errorMap.values()));
        return new ResponseEntity<>(customErrorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleDateParseErrorException(DateTimeParsingException exception){
        CustomErrorMessage<String> customErrorMessage =new CustomErrorMessage<>();
        customErrorMessage.setStatus("DateTimeConversion Error");
        customErrorMessage.setTimestamp(System.currentTimeMillis());
        customErrorMessage.setMessage(exception.getMessage());
        return new ResponseEntity<>(customErrorMessage,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleResourceConflictException(ResourceConflictException exception){
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

}
