package com.example.contactapp.controllers;

import com.example.contactapp.services.AddressService;
import com.example.contactapp.services.PhoneNumberService;
import com.example.contactapp.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    private AddressService addressService;

    private PhoneNumberService phoneNumberService;

    public UserController(UserService userService, AddressService addressService, PhoneNumberService phoneNumberService) {
        this.userService = userService;
        this.addressService = addressService;
        this.phoneNumberService = phoneNumberService;
    }
}
