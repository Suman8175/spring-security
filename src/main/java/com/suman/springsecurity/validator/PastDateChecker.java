package com.suman.springsecurity.validator;


import com.suman.springsecurity.exception.DateTimeParsingException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class PastDateChecker implements ConstraintValidator<PastDateCheck,String> {
    @Override
    public boolean isValid(String date, ConstraintValidatorContext constraintValidatorContext) {
        try{
            DateTimeFormatter formatter =DateTimeFormatter.ofPattern("yyyy-MM-dd");
            System.out.println("Triggered");
            LocalDate parsedDate=LocalDate.parse(date,formatter);
            return parsedDate.isBefore(LocalDate.now());
        }
        catch (DateTimeParseException exception){
            throw new DateTimeParsingException("Invalid date format.Date should be in yyyy-MM-dd");
        }
    }
}
