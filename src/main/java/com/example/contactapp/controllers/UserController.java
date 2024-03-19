package com.example.contactapp.controllers;

import com.example.contactapp.domain.dto.incoming.CreateUserInfoDto;
import com.example.contactapp.services.UserService;
import com.example.contactapp.validators.UserInfoValidator;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final UserInfoValidator userInfoValidator;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService, UserInfoValidator userInfoValidator) {
        this.userService = userService;
        this.userInfoValidator = userInfoValidator;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userInfoValidator);
    }

    @PostMapping("/new-contact")
    public ResponseEntity<Void> createNewContact(@Valid @RequestBody CreateUserInfoDto createUserInfoDto) {
        logger.info("Received request to create new contact");
        if (userService.createNewUserInfo(createUserInfoDto)) {
            logger.info("Contact created successfully");
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            logger.error("Failed to create contact");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
