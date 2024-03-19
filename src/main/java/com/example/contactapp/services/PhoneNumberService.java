package com.example.contactapp.services;

import com.example.contactapp.domain.PhoneNumber;
import com.example.contactapp.domain.dto.incoming.CreatePhoneNumberDto;
import com.example.contactapp.repositories.PhoneNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PhoneNumberService {

    private PhoneNumberRepository phoneNumberRepository;

    @Autowired
    public PhoneNumberService(PhoneNumberRepository phoneNumberRepository) {
        this.phoneNumberRepository = phoneNumberRepository;
    }

    public boolean checkPhoneNumbersExist(List<CreatePhoneNumberDto> phoneNumberDTOs) {
        for (CreatePhoneNumberDto phoneNumberDTO : phoneNumberDTOs) {
            PhoneNumber phoneNumberToFind = phoneNumberRepository.findPhoneNumberByNumber(phoneNumberDTO.getPhoneNumber());
            if (phoneNumberToFind != null) {
                return true;
            }
        }
        return false;
    }
}

