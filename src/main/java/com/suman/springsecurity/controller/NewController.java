package com.suman.springsecurity.controller;

import com.suman.springsecurity.dto.UserUpdate;
import com.suman.springsecurity.validator.ExcludeEndpoint;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "New Suman",description = "This is for handling all users documentation")
@RequestMapping("/address")
@CrossOrigin("*")
public class NewController {

    @ExcludeEndpoint
    @PutMapping("/{hero}")
    public ResponseEntity<?> updateExistingUserById(@RequestBody @Valid UserUpdate userUpdate, @PathVariable Integer userId){

        return null;
    }

    @ExcludeEndpoint
    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        return null;
    }
}
