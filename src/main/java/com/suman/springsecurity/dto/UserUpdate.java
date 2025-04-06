package com.suman.springsecurity.dto;

import com.suman.springsecurity.validator.PastDateCheck;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserUpdate(@NotBlank(message = "user name cannot be blank")
                         String userName,
//                         @NotBlank(message = "phone number cannot be blank")
                         Long userPhoneNumber,

                         @PastDateCheck(message = "Past date should be used")
                         String userDOB) {
}
