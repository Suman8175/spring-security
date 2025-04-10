package com.suman.springsecurity.services.inter;

import com.suman.springsecurity.dto.AddressDTO;
import com.suman.springsecurity.entity.Address;

public interface AddressService {

    Address createAddress(AddressDTO.AddressCreateDTO address);
    Address updateAddress(AddressDTO.AddressUpdateDTO address);
}
