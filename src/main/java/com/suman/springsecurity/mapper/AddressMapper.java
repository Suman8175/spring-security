package com.suman.springsecurity.mapper;

import com.suman.springsecurity.dto.AddressDTO;
import com.suman.springsecurity.entity.Address;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

@Service

public class AddressMapper {

    public Address mapCreateAddressToAddressEntity(AddressDTO.AddressCreateDTO addressCreateDTO){
        Address address =new Address();
        address.setCity(addressCreateDTO.city());
        address.setCountry(addressCreateDTO.country());
        address.setHouseNumber(addressCreateDTO.houseNumber());
        return address;
    }

    public AddressDTO.AddressResponseDTO mapAddressEntityToAddressResponseDTO(Address address){
        AddressDTO.AddressResponseDTO addressResponseDTO =new AddressDTO.AddressResponseDTO(address.getAddressId(),address.getCountry(),address.getCity(),address.getHouseNumber());
        return addressResponseDTO;
    }
    public Address mapAddressUpdateDTOToAddress(AddressDTO.AddressUpdateDTO addressUpdateDTO){
        Address address =new Address();
        address.setAddressId(addressUpdateDTO.addressId());
        address.setCountry(addressUpdateDTO.country());
        address.setCity(addressUpdateDTO.city());
        address.setHouseNumber(addressUpdateDTO.houseNumber());
        return address;
    }

    public Address mapOriginalAddressToUpdatedAddress(Address originalAddress,Address newAddress){
        originalAddress.setCountry(newAddress.getCountry());
        originalAddress.setCity(newAddress.getCity());
        originalAddress.setHouseNumber(newAddress.getHouseNumber());
        return originalAddress;
    }

}
