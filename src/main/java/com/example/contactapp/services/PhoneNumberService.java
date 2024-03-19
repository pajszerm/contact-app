package com.example.contactapp.services;

import com.example.contactapp.repositories.PhoneNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PhoneNumberService {

    private PhoneNumberRepository phoneNumberRepository;

    @Autowired
    public PhoneNumberService(PhoneNumberRepository phoneNumberRepository) {
        this.phoneNumberRepository = phoneNumberRepository;
    }
}

