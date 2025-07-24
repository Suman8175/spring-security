package com.suman.springsecurity.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suman.springsecurity.dto.AddressDTO;
import com.suman.springsecurity.dto.UserCreate;
import com.suman.springsecurity.dto.UserResponse;
import com.suman.springsecurity.exception.GlobalExceptionHandler;
import com.suman.springsecurity.services.inter.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;
    private UserCreate userCreate;
    private UserCreate missingUserDetails;
    private AddressDTO.AddressCreateDTO address;
    private AddressDTO.AddressResponseDTO mockAddress;
    private UserResponse mockUserResponse;


    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setControllerAdvice(GlobalExceptionHandler.class).build();
        address =new AddressDTO.AddressCreateDTO("UB","Qwerty",12);

        userCreate =new UserCreate("Suman Devkota",9876543210L,"","2020-01-02","password",address);


        userCreate =new UserCreate("Suman Devkota",9876543210L,"suman@gmail.com","2020-01-02","password",address);


        mockAddress =new AddressDTO.AddressResponseDTO(1,"UB","Qwerty",12);
        mockUserResponse=new UserResponse(1,"Suman Devkota",9876543210L,"suman@gmail.com","2020-01-02",mockAddress);
    }

    @Test
    void createUser_Success(){
        //Arrange
        when(userService.createUser(any(UserCreate.class))).thenReturn(mockUserResponse);

        //Act
        ResponseEntity<UserResponse> result = userController.createUser(userCreate);

        //Assert
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(result.getBody()).isNotNull();

    }

    @Test
    void creatUser_ThrowsException() throws Exception {
        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(missingUserDetails)))
                .andExpect(status().isBadRequest());

    }


    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getAllUsers_Success(){
        UserResponse response=new UserResponse(2,"Arpan Thapa",9876543210L,"arpan@gmail.com","2010-01-02",mockAddress);
        when(userService.listOfUsers()).thenReturn(List.of(mockUserResponse,response));

        ResponseEntity<?> allUsers = userController.getAllUsers();
        List<UserResponse> body = (List) allUsers.getBody();
        Assertions.assertThat(allUsers).isNotNull();
        Assertions.assertThat(allUsers.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(body.get(0)).isEqualTo(mockUserResponse);
        Assertions.assertThat(body.get(1)).isEqualTo(response);
        Assertions.assertThat(body.size()).isEqualTo(2);
    }


}
