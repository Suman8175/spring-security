package com.suman.springsecurity.dto;

import jakarta.validation.constraints.NotBlank;

public record AddressCreateDTO(
        @NotBlank(message = "country cannot be blank")
        String country,

        @NotBlank(message = "city cannot be blank")
        String city,
        int houseNumber
) {
}
