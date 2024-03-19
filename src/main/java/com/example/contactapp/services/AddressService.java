package com.example.contactapp.services;

import com.example.contactapp.domain.Address;
import com.example.contactapp.domain.UserInfo;
import com.example.contactapp.domain.dto.incoming.CreateAddressDto;
import com.example.contactapp.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public void saveAddressForUser(CreateAddressDto addressDto, UserInfo userInfo) {
        Address addressToSave = mapAddressDtoToAddress(addressDto);
        addressToSave.setUser(userInfo);
        addressRepository.save(addressToSave);
    }

    private Address mapAddressDtoToAddress(CreateAddressDto addressDto) {
        Address address = new Address();
        address.setCityName(addressDto.getCityName());
        address.setHouseNumber(addressDto.getHouseNumber());
        address.setPostalCode(addressDto.getPostalCode());
        address.setStreetName(addressDto.getStreetName());
        return address;
    }
}
