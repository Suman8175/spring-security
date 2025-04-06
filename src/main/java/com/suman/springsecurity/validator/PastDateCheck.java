package com.suman.springsecurity.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PastDateChecker.class)
public @interface PastDateCheck {
    public String message() default "Date should be of past date";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
