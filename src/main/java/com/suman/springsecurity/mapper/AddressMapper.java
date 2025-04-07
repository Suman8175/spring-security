package com.suman.springsecurity.mapper;

import com.suman.springsecurity.dto.AddressCreateDTO;
import com.suman.springsecurity.dto.AddressResponseDTO;
import com.suman.springsecurity.entity.Address;
import org.springframework.stereotype.Service;

@Service

public class AddressMapper {

    public Address mapCreateAddressToAddressEntity(AddressCreateDTO addressCreateDTO){
        Address address =new Address();
        address.setCity(addressCreateDTO.city());
        address.setCountry(addressCreateDTO.country());
        address.setHouseNumber(addressCreateDTO.houseNumber());
        return address;
    }

    public AddressResponseDTO mapAddressEntityToAddressResponseDTO(Address address){
        AddressResponseDTO addressResponseDTO =new AddressResponseDTO(address.getAddressId(),address.getCountry(),address.getCity(),address.getHouseNumber());
        return addressResponseDTO;
    }

}
