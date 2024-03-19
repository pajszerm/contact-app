package com.example.contactapp.controllers;

import com.example.contactapp.domain.dto.incoming.CreateUserInfoDto;
import com.example.contactapp.services.AddressService;
import com.example.contactapp.services.PhoneNumberService;
import com.example.contactapp.services.UserService;
import com.example.contactapp.validators.UserInfoValidator;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;
    private UserInfoValidator userInfoValidator;

    public UserController(UserService userService, UserInfoValidator userInfoValidator) {
        this.userService = userService;
        this.userInfoValidator = userInfoValidator;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {binder.addValidators(userInfoValidator);}

    @PostMapping("/new-contact")
    public ResponseEntity<Void> createNewContact(@Valid @RequestBody CreateUserInfoDto createUserInfoDto) {
        if (userService.createNewContact(createUserInfoDto)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
