package com.suman.springsecurity.service;

import com.suman.springsecurity.dto.AddressDTO;
import com.suman.springsecurity.entity.Address;
import com.suman.springsecurity.exception.ResourceNotFoundException;
import com.suman.springsecurity.mapper.AddressMapper;
import com.suman.springsecurity.repository.AddressRepository;
import com.suman.springsecurity.services.impls.AddressServiceImpls;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class AddressServiceImplsTest {

    @Mock
    private  AddressRepository addressRepository;

    @Mock
    private  AddressMapper addressMapper;


    @InjectMocks
    private  AddressServiceImpls addressServiceImpls;

    private Address expectedValue;
    private AddressDTO.AddressCreateDTO addressCreateDTO;
    private Address actualValue;
    private AddressDTO.AddressUpdateDTO addressUpdateDTO;
    private Address expectedAddressToUpdate;
    private Address mappedAddress;


    @BeforeEach
    void setUp(){
        expectedValue =new Address();
        expectedValue.setCountry("NP");
        expectedValue.setCity("Pokhara");
        expectedValue.setHouseNumber(12);

    addressCreateDTO =new AddressDTO.AddressCreateDTO("NP","Pokhara",12);

    actualValue =new Address();
    actualValue.setAddressId(1);
    actualValue.setCountry("NP");
    actualValue.setCity("Pokhara");
    actualValue.setHouseNumber(12);

    addressUpdateDTO =new AddressDTO.AddressUpdateDTO(1,"IN","Mumbai",10);

        expectedAddressToUpdate =new Address();
        expectedAddressToUpdate.setAddressId(1);
        expectedAddressToUpdate.setCountry("IN");
        expectedAddressToUpdate.setCity("Mumbai");
        expectedAddressToUpdate.setHouseNumber(10);

        mappedAddress =new Address();
        mappedAddress.setAddressId(1);
        mappedAddress.setCountry("IN");
        mappedAddress.setCity("Mumbai");
        mappedAddress.setHouseNumber(10);
    }

    @Test
    public void createAddress_ReturnsSavedAddress(){

        //Arrange
        Mockito.when(addressMapper.mapCreateAddressToAddressEntity(addressCreateDTO)).thenReturn(expectedValue);
        Mockito.when(addressRepository.save(expectedValue)).thenReturn(actualValue);

        //Act
        Address result = addressServiceImpls.createAddress(addressCreateDTO);

        //Assert
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getAddressId()).isEqualTo(1);
        Assertions.assertThat(result.getCountry()).isEqualTo("NP");
        Assertions.assertThat(result.getCity()).isEqualTo("Pokhara");
        Assertions.assertThat(result.getHouseNumber()).isEqualTo(12);
    }


    @Test
    public void updateAddress_ThrowsResourceNotFound_OnAddressNotFound(){
        //Arrange
        Mockito.when(addressMapper.mapAddressUpdateDTOToAddress(addressUpdateDTO)).thenReturn(expectedAddressToUpdate);
        Mockito.when(addressRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,()->addressServiceImpls.updateAddress(addressUpdateDTO));
    }


    @Test
    public void updateAddress_ReturnsUpdatedAddress(){
        //Arrange
        Mockito.when(addressMapper.mapAddressUpdateDTOToAddress(addressUpdateDTO)).thenReturn(expectedAddressToUpdate);
        Mockito.when(addressRepository.findById(1)).thenReturn(Optional.of(actualValue));
        Mockito.when(addressMapper.mapOriginalAddressToUpdatedAddress(actualValue,expectedAddressToUpdate)).thenReturn(mappedAddress);
        Mockito.when(addressRepository.save(mappedAddress)).thenReturn(expectedAddressToUpdate);

        //Act
        Address result = addressServiceImpls.updateAddress(addressUpdateDTO);

//        Assert
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getAddressId()).isEqualTo(1);
        Assertions.assertThat(result.getCountry()).isEqualTo("IN");

    }



}
