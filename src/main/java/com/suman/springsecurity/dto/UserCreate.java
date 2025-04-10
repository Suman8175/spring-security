package com.suman.springsecurity.dto;

import com.suman.springsecurity.validator.PastDateCheck;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserCreate(

                         @NotBlank(message = "user name cannot be blank")
                         String userName,


//                         @NotBlank(message = "phone number cannot be blank")
                         Long userPhoneNumber,

                         @Email(message = "provide a valid email")
                         @NotBlank(message = "email cannot be empty")
                         String userEmail,

                         @PastDateCheck(message = "Past date should be used")
                         String userDOB,

                         @NotBlank(message = "Password cannot be empty")
                         String userPassword,

                         AddressDTO.AddressCreateDTO address
                         ) {
}
