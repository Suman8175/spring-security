package com.suman.springsecurity.controller;

import com.suman.springsecurity.dto.UserCreate;
import com.suman.springsecurity.dto.UserLogin;
import com.suman.springsecurity.dto.UserResponse;
import com.suman.springsecurity.dto.UserUpdate;
import com.suman.springsecurity.services.inter.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "UserController",description = "This is for handling all users documentation")
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;


    @Operation(
            summary = "Used for create users",
            description = "This API is for creating users",
            responses = {@ApiResponse(responseCode = "201",description = "Success"),
                        @ApiResponse(responseCode = "400",description = "Bad request"),
                        @ApiResponse(responseCode = "409",description = "Conflict")}
    )
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody @Valid UserCreate userCreate){
        UserResponse user = userService.createUser(userCreate);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }


    @Operation(
            summary = "Used for fetching all users",
            description = "This API is used for fetching all users",
            responses = {@ApiResponse(responseCode = "200",description = "Success")}
    )
    @GetMapping
    public ResponseEntity<?> getAllUsers(){
       return new ResponseEntity<>( userService.listOfUsers(),HttpStatus.OK);
    }


    @Operation(
            summary = "Fetching individual user by userId",
            description = "This API is used for fetching individual user by userId",
            responses = {@ApiResponse(responseCode = "404",description = "User By userId not found"),
                         @ApiResponse(responseCode = "200",description = "Success")}
    )
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id){
    return new ResponseEntity<>(userService.getUserById(id),HttpStatus.OK);
    }


    @Operation(summary = "Updating user by userId",
            description = "This API is used to update users based on id and new details",
    responses = {@ApiResponse(responseCode = "200",description = "Success")})
    @PutMapping("/{userId}")
    public ResponseEntity<?> updateExistingUserById(@RequestBody @Valid UserUpdate userUpdate, @PathVariable Integer userId){

        return new ResponseEntity<>(userService.updateUser(userUpdate,userId),HttpStatus.OK);
    }

    @Operation(summary = "Delete user by userId",
    description = "This API delete existing user by userId",
    responses = {@ApiResponse(responseCode = "206",description = "No content")})
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUserByUserId(@PathVariable Integer userId){
       userService.deleteExistingUserByUserId(userId);
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLogin userLogin){
       return new ResponseEntity<>(userService.sendsResponseAfterScuessfulLogin(userLogin),HttpStatus.OK);
    }

}
