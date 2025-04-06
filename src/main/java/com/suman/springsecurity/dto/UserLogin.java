package com.suman.springsecurity.dto;

public record UserLogin(
        String userEmail,
        String userPassword
) {
}
