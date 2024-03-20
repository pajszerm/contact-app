package com.example.contactapp.controllers;

import com.example.contactapp.domain.dto.incoming.CreateUserInfoDto;
import com.example.contactapp.domain.dto.outgoing.UserDetailsResponse;
import com.example.contactapp.services.UserService;
import com.example.contactapp.validators.UserInfoValidator;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final UserInfoValidator userInfoValidator;

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
        if (userService.createNewUserInfo(createUserInfoDto)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<UserDetailsResponse> getAllContacts(@RequestParam int page, @RequestParam int size) {
        return new ResponseEntity<>(userService.getAllUserDetails(page, size), HttpStatus.OK);
    }

    @DeleteMapping("/deleteContact/{username}")
    public ResponseEntity<Void> deleteContact(@PathVariable("username") String username) {
        userService.deleteUserInfo(username);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
