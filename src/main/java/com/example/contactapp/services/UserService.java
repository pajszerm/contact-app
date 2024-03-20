package com.example.contactapp.services;

import com.example.contactapp.domain.Address;
import com.example.contactapp.domain.PhoneNumber;
import com.example.contactapp.domain.UserInfo;
import com.example.contactapp.domain.dto.incoming.CreateAddressDto;
import com.example.contactapp.domain.dto.incoming.CreatePhoneNumberDto;
import com.example.contactapp.domain.dto.incoming.CreateUserInfoDto;
import com.example.contactapp.domain.dto.outgoing.AddressDetails;
import com.example.contactapp.domain.dto.outgoing.PhoneNumberDetails;
import com.example.contactapp.domain.dto.outgoing.UserDetails;
import com.example.contactapp.domain.dto.outgoing.UserDetailsResponse;
import com.example.contactapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
        UserInfo userToFind = userRepository.findUserByUsername(username);
        return userToFind != null;
    }

    public boolean checkEmailExists(String email) {
        UserInfo userToFind = userRepository.findUserByEmail(email);
        return userToFind != null;
    }

    public boolean checkTajNumberExists(String tajNumber) {
        UserInfo userToFind = userRepository.findUserByTajNumber(tajNumber);
        return userToFind != null;
    }

    public boolean checkTaxNumberExists(String taxNumber) {
        UserInfo userToFind = userRepository.findUserByTaxNumber(taxNumber);
        return userToFind != null;
    }

    public boolean createNewUserInfo(CreateUserInfoDto createUserInfoDto) {
        UserInfo userInfo = mapCreateUserInfoDtoToUserInfo(createUserInfoDto);
        userInfo = userRepository.save(userInfo);

        for (CreateAddressDto addressDto : createUserInfoDto.getAddresses()) {
            addressService.saveAddressForUser(addressDto, userInfo);
        }

        for (CreatePhoneNumberDto phoneNumberDto : createUserInfoDto.getPhoneNumbers()) {
            phoneNumberService.savePhoneNumberForUser(phoneNumberDto, userInfo);
        }

        return userInfo.getId() != null;
    }

    private UserInfo mapCreateUserInfoDtoToUserInfo(CreateUserInfoDto createUserInfoDto) {
        UserInfo userInfo = new UserInfo();
        userInfo.setName(createUserInfoDto.getName());
        userInfo.setUsername(createUserInfoDto.getUsername());
        userInfo.setMothersName(createUserInfoDto.getMothersName());
        if (createUserInfoDto.getEmail().isBlank()) {
            userInfo.setEmail(null);
        } else {
            userInfo.setEmail(createUserInfoDto.getEmail());
        }
        userInfo.setBirthDate(createUserInfoDto.getBirthDate());
        userInfo.setTajNumber(createUserInfoDto.getTajNumber());
        userInfo.setTaxNumber(createUserInfoDto.getTaxNumber());
        return userInfo;
    }


    public UserDetailsResponse getAllUserDetails(int page, int size) {
        Page<UserInfo> users = userRepository.findAllUsers(PageRequest.of(page, size));
        List<UserDetails> userDetails = processUserDetails(users.getContent());
        UserDetailsResponse response = constructUserDetailsResponse(userDetails, users, page, size);
        return response;
    }

    private List<UserDetails> processUserDetails(List<UserInfo> userList) {
        List<UserDetails> userDetails = new ArrayList<>();
        for (UserInfo userInfo : userList) {
            UserDetails details = createUserDetails(userInfo);
            List<PhoneNumberDetails> phoneNumberDetails = processPhoneNumbers(userInfo.getPhoneNumbers());
            List<AddressDetails> addressDetails = processAddresses(userInfo.getAddresses());
            details.setPhoneNumbers(phoneNumberDetails);
            details.setAddresses(addressDetails);
            userDetails.add(details);
        }
        return userDetails;
    }

    private UserDetails createUserDetails(UserInfo userInfo) {
        UserDetails details = new UserDetails();
        details.setUsername(userInfo.getUsername());
        details.setName(userInfo.getName());
        details.setMothersName(userInfo.getMothersName());
        details.setEmail(userInfo.getEmail() == null ? "" : userInfo.getEmail());
        details.setBirthDate(userInfo.getBirthDate().toString());
        details.setTajNumber(userInfo.getTaxNumber());
        details.setTaxNumber(userInfo.getTaxNumber());
        return details;
    }

    private List<PhoneNumberDetails> processPhoneNumbers(List<PhoneNumber> phoneNumbers) {
        List<PhoneNumberDetails> phoneNumberDetails = new ArrayList<>();
        for (PhoneNumber phoneNumber : phoneNumbers) {
            PhoneNumberDetails details = new PhoneNumberDetails();
            details.setPhoneNumber(phoneNumber.getNumber());
            phoneNumberDetails.add(details);
        }
        return phoneNumberDetails;
    }

    private List<AddressDetails> processAddresses(List<Address> addresses) {
        List<AddressDetails> addressDetails = new ArrayList<>();
        for (Address address : addresses) {
            AddressDetails details = new AddressDetails();
            details.setCityName(address.getCityName());
            details.setStreetName(address.getStreetName());
            details.setHouseNumber(address.getHouseNumber());
            details.setPostalCode(address.getPostalCode());
            addressDetails.add(details);
        }
        return addressDetails;
    }

    private UserDetailsResponse constructUserDetailsResponse(List<UserDetails> userDetails, Page<UserInfo> users, int page, int size) {
        UserDetailsResponse response = new UserDetailsResponse();
        response.setUsers(userDetails);
        response.setLength(users.getTotalElements());
        response.setPageIndex(page);
        response.setPageSize(size);
        return response;
    }

    public void deleteUserInfo(String username) {
        UserInfo userToDelete = userRepository.findUserByUsername(username);
        userRepository.delete(userToDelete);
    }
}
