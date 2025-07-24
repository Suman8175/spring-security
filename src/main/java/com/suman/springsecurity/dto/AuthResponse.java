package com.suman.springsecurity.dto;

public record AuthResponse(
        String accessToken,
        int accessTokenExpiryType
) {
}
