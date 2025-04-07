package com.suman.springsecurity.service;


import com.suman.springsecurity.dto.AddressCreateDTO;
import com.suman.springsecurity.dto.AddressResponseDTO;
import com.suman.springsecurity.dto.UserCreate;
import com.suman.springsecurity.dto.UserResponse;
import com.suman.springsecurity.entity.Address;
import com.suman.springsecurity.entity.User;
import com.suman.springsecurity.mapper.UserMapper;
import com.suman.springsecurity.repository.UserRepository;
import com.suman.springsecurity.services.impls.UserServiceImpls;
import com.suman.springsecurity.services.inter.AddressService;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplsTest {

    @Mock
    private UserRepository userRepository ;

    @Mock
    private AddressService addressService ;

    @Mock
    private UserMapper userMapper ;


    @InjectMocks
    private UserServiceImpls userServiceImpls ;

    private UserCreate userCreate;
    private AddressCreateDTO addressCreateDTO;
    private User user;
    private Address address;
    private UserResponse userResponse;
    private AddressResponseDTO addressResponseDTO;

    @BeforeEach
    void setUp(){

        addressCreateDTO =new AddressCreateDTO("Np","Pokhara",14);
        userCreate =new UserCreate("Suman",9876543210L,"suman@gmail.com","2021-01-01","password",addressCreateDTO);
        address =new Address();
        address.setAddressId(1);
        address.setCountry("Np");
        address.setCity("Pokhara");
        address.setHouseNumber(14);
        user =new User();
        user.setUserId(1);
        user.setUserEmail("suman@gmail.com");
        user.setUserPhoneNumber(9876543210L);
        user.setUserName("Suman");
        user.setUserPassword("password");
        user.setAddress(address);
        addressResponseDTO =new AddressResponseDTO(1,"Np","Pokhara",14);
        userResponse = new UserResponse(1,"Suman",9876543210L,"suman@gmail.com","2021-01-01",addressResponseDTO);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        user.setUserDOB( LocalDate.parse("2021-01-01",formatter));
    }


    @Test
    @Transactional
     public void createUser_Success() {

        //Arrange
        Mockito.when(userMapper.mapCreateUserToUserEntity(userCreate)).thenReturn(user);
        Mockito.when(userRepository.existsByUserEmail("suman@gmail.com")).thenReturn(false);
        Mockito.when(addressService.createAddress(addressCreateDTO)).thenReturn(address);
        Mockito.when(userRepository.save(user)).thenReturn(user);
        Mockito.when(userMapper.mapUserEntityToUserResponse(user)).thenReturn(userResponse);

        //Act
        UserResponse result = userServiceImpls.createUser(userCreate);

        //Assert
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(userResponse.addressResponseDTO()).isEqualTo(addressResponseDTO);
        Assertions.assertThat(userResponse.userEmail()).isEqualTo("suman@gmail.com");

    }

    @Test
    @Transactional
    public void getListOfUsers_ReturnsListOfUserResponse(){
        //Arrange
        Mockito.when(userRepository.findAll()).thenReturn(List.of(user));
        Mockito.when(userMapper.mapUserEntityToUserResponse(user)).thenReturn(userResponse);

        //Act
        List<UserResponse> userResponses = userServiceImpls.listOfUsers();

        //Assert
        Assertions.assertThat(userResponses).isNotNull();
        Assertions.assertThat(userResponses).isInstanceOf(List.class);
        Assertions.assertThat(userResponses).allMatch(Objects::nonNull);

    }

    @Test
    public void getUserById_ReturnsUserResponseById(){
        //Arrange
        Mockito.when(userRepository.findById(1)).thenReturn(Optional.ofNullable(user));
        Mockito.when(userMapper.mapUserEntityToUserResponse(user)).thenReturn(userResponse);

        //Act
        UserResponse userById = userServiceImpls.getUserById(1);

        //Assert
        Assertions.assertThat(userById).isNotNull();
        Assertions.assertThat(userById.userId()).isEqualTo(1);
        Assertions.assertThat(userById.addressResponseDTO()).isEqualTo(addressResponseDTO);

    }



}
