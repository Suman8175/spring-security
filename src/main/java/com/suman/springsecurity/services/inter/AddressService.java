package com.suman.springsecurity.services.inter;

import com.suman.springsecurity.dto.AddressCreateDTO;
import com.suman.springsecurity.dto.AddressResponseDTO;
import com.suman.springsecurity.entity.Address;

public interface AddressService {

    Address createAddress(AddressCreateDTO address);
}
