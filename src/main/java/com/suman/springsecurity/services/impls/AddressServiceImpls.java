package com.suman.springsecurity.services.impls;

import com.suman.springsecurity.dto.AddressCreateDTO;
import com.suman.springsecurity.entity.Address;
import com.suman.springsecurity.mapper.AddressMapper;
import com.suman.springsecurity.repository.AddressRepository;
import com.suman.springsecurity.services.inter.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpls  implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    @Override
    public Address createAddress(AddressCreateDTO address) {
        Address addressToSave = addressMapper.mapCreateAddressToAddressEntity(address);
        return addressRepository.save(addressToSave);
    }
}
