package com.suman.springsecurity.dto;

public record UserResponse(
        Integer userId,
        String userName,
        Long userPhoneNumber,
        String userEmail,
        String userDOB,
        AddressResponseDTO addressResponseDTO
) {
}
