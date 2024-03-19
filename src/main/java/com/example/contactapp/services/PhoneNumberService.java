package com.example.contactapp.services;

import com.example.contactapp.domain.PhoneNumber;
import com.example.contactapp.domain.UserInfo;
import com.example.contactapp.domain.dto.incoming.CreatePhoneNumberDto;
import com.example.contactapp.repositories.PhoneNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PhoneNumberService {

    private final PhoneNumberRepository phoneNumberRepository;

    @Autowired
    public PhoneNumberService(PhoneNumberRepository phoneNumberRepository) {
        this.phoneNumberRepository = phoneNumberRepository;
    }

    public boolean checkPhoneNumbersExist(List<CreatePhoneNumberDto> phoneNumberDTOs) {
        for (CreatePhoneNumberDto phoneNumberDTO : phoneNumberDTOs) {
            String phoneNumber = phoneNumberDTO.getPhoneNumber();
            PhoneNumber phoneNumberToFind = phoneNumberRepository.findPhoneNumberByNumber(phoneNumber);
            if (phoneNumberToFind != null) {
                return true;
            }
        }
        return false;
    }

    public void savePhoneNumberForUser(CreatePhoneNumberDto phoneNumberDto, UserInfo userInfo) {
        PhoneNumber phoneNumberToSave = mapCreatePhoneNumberDtoToPhoneNumber(phoneNumberDto);
        phoneNumberToSave.setUser(userInfo);
        phoneNumberRepository.save(phoneNumberToSave);
    }

    private PhoneNumber mapCreatePhoneNumberDtoToPhoneNumber(CreatePhoneNumberDto phoneNumberDto) {
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setNumber(phoneNumberDto.getPhoneNumber());
        return phoneNumber;
    }
}

