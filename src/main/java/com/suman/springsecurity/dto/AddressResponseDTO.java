package com.suman.springsecurity.dto;

import jakarta.validation.constraints.NotBlank;

public record AddressResponseDTO(
        Integer addressId,
        String country,
        String city,
        Integer houseNumber
) {
}
