package com.example.contactapp.services;

import com.example.contactapp.domain.UserInfo;
import com.example.contactapp.domain.dto.incoming.CreateAddressDto;
import com.example.contactapp.domain.dto.incoming.CreatePhoneNumberDto;
import com.example.contactapp.domain.dto.incoming.CreateUserInfoDto;
import com.example.contactapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    private final AddressService addressService;

    private final PhoneNumberService phoneNumberService;

    @Autowired
    public UserService(UserRepository userRepository,
                       AddressService addressService,
                       PhoneNumberService phoneNumberService) {
        this.userRepository = userRepository;
        this.addressService = addressService;
        this.phoneNumberService = phoneNumberService;
    }

    public boolean checkUserNameExists(String username) {
        UserInfo userToFind = userRepository.findUserInfoByUsername(username);
        return userToFind != null;
    }

    public boolean checkEmailExists(String email) {
        UserInfo userToFind = userRepository.findUserByEmail(email);
        return userToFind != null;
    }

    public boolean createNewUserInfo(CreateUserInfoDto createUserInfoDto) {
        UserInfo userInfo = mapCreateUserInfoDtoToUserInfo(createUserInfoDto);
        userInfo = userRepository.save(userInfo);

        for (CreateAddressDto addressDto : createUserInfoDto.getAddressDTOs()) {
            addressService.saveAddressForUser(addressDto, userInfo);
        }

        for (CreatePhoneNumberDto phoneNumberDto : createUserInfoDto.getPhoneNumberDTOs()) {
            phoneNumberService.savePhoneNumberForUser(phoneNumberDto, userInfo);
        }

        return userInfo.getId() != null;
    }

    private UserInfo mapCreateUserInfoDtoToUserInfo(CreateUserInfoDto createUserInfoDto) {
        UserInfo userInfo = new UserInfo();
        userInfo.setName(createUserInfoDto.getName());
        userInfo.setUsername(createUserInfoDto.getUsername());
        userInfo.setMothersName(createUserInfoDto.getMothersName());
        userInfo.setEmail(createUserInfoDto.getEmail());
        userInfo.setBirthDate(createUserInfoDto.getBirthDate());
        userInfo.setTajNumber(createUserInfoDto.getTajNumber());
        userInfo.setTaxNumber(createUserInfoDto.getTaxNumber());
        return userInfo;
    }
}
