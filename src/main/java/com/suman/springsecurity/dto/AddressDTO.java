package com.suman.springsecurity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AddressDTO {

    //For creating address
    public  record AddressCreateDTO(
            @NotBlank(message = "country cannot be blank")
            String country,

            @NotBlank(message = "city cannot be blank")
            String city,
            int houseNumber
    ) {
    }

    //For addressResponse
    public record AddressResponseDTO(
            Integer addressId,
            String country,
            String city,
            Integer houseNumber
    ) {
    }


    //For requesting address When  user is updated
    public record AddressUpdateDTO(
            @NotNull
            Integer addressId,
            @NotBlank(message = "country cannot be blank")
            String country,

            @NotBlank(message = "city cannot be blank")
            String city,
            Integer houseNumber
    ) {
    }


}
