package com.suman.springsecurity.services.impls;

import com.suman.springsecurity.dto.AddressDTO;
import com.suman.springsecurity.entity.Address;
import com.suman.springsecurity.exception.ResourceNotFoundException;
import com.suman.springsecurity.mapper.AddressMapper;
import com.suman.springsecurity.repository.AddressRepository;
import com.suman.springsecurity.services.inter.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressServiceImpls  implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;


    @Override
    public Address createAddress(AddressDTO.AddressCreateDTO address) {
        Address addressToSave = addressMapper.mapCreateAddressToAddressEntity(address);
        return addressRepository.save(addressToSave);
    }

    @Override
    public Address updateAddress(AddressDTO.AddressUpdateDTO address) {
        Address addressToUpdate=addressMapper.mapAddressUpdateDTOToAddress(address);
        Optional<Address> byId = addressRepository.findById(addressToUpdate.getAddressId());
        if (byId.isEmpty()){
            throw new ResourceNotFoundException("Address not found");
        }
        Address originalAddress = byId.get();
        Address mappedAddress = addressMapper.mapOriginalAddressToUpdatedAddress(originalAddress, addressToUpdate);
        return addressRepository.save(mappedAddress);
    }



}
