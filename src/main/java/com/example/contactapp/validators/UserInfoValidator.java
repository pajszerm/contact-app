package com.example.contactapp.validators;

import com.example.contactapp.domain.dto.incoming.CreatePhoneNumberDto;
import com.example.contactapp.domain.dto.incoming.CreateUserInfoDto;
import com.example.contactapp.services.PhoneNumberService;
import com.example.contactapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.util.List;

@Component
public class UserInfoValidator implements Validator {

    private final UserService userService;

    private final PhoneNumberService phoneNumberService;

    @Autowired
    public UserInfoValidator(UserService userService, PhoneNumberService phoneNumberService) {
        this.userService = userService;
        this.phoneNumberService = phoneNumberService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return CreateUserInfoDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        CreateUserInfoDto createUserInfoDto = (CreateUserInfoDto) target;

        if (createUserInfoDto.getUsername() == null || createUserInfoDto.getUsername().isBlank()) {
            errors.rejectValue("username", "username.is.invalid");
        }
        if (userService.checkUserNameExists(createUserInfoDto.getUsername())) {
            errors.rejectValue("username", "username.is.already.exists");
        }
        if (createUserInfoDto.getMothersName() == null || createUserInfoDto.getMothersName().isBlank()) {
            errors.rejectValue("mothersName", "mothersName.is.invalid");
        }
        if (createUserInfoDto.getBirthDate() == null || createUserInfoDto.getBirthDate().isAfter(LocalDate.now())) {
            errors.rejectValue("birthDate", "birth.date.is.invalid");
        }
        if (createUserInfoDto.getTajNumber() == null) {
            errors.rejectValue("tajNumber", "taj.number.is.invalid");
        }
        if (userService.checkTajNumberExists(createUserInfoDto.getTajNumber())) {
            errors.rejectValue("tajNumber", "taj.number.is.already.exists");
        }
        if (createUserInfoDto.getTaxNumber() == null) {
            errors.rejectValue("taxNumber", "tax.number.is.invalid");
        }
        if (userService.checkTaxNumberExists(createUserInfoDto.getTaxNumber())) {
            errors.rejectValue("tajNumber", "taj.number.is.already.taken");
        }
        if (createUserInfoDto.getEmail() == null || createUserInfoDto.getEmail().isBlank()
                && (createUserInfoDto.getPhoneNumberDTOs().isEmpty())) {
            errors.rejectValue("email", "email.is.invalid");
        }
        if (userService.checkEmailExists(createUserInfoDto.getEmail())) {
            errors.rejectValue("email", "email.is.already.exists");
        }
        if (createUserInfoDto.getAddressDTOs().isEmpty()) {
            errors.rejectValue("address", "address.is.invalid");
        }
        List<CreatePhoneNumberDto> phoneNumberDTOs = createUserInfoDto.getPhoneNumberDTOs();
        if (!phoneNumberDTOs.isEmpty()) {
            if (phoneNumberDTOs.stream().anyMatch(dto -> dto.getPhoneNumber() == null || dto.getPhoneNumber().isBlank())) {
                errors.rejectValue("phoneNumberDTOs", "phone.number.is.invalid");
            } else {
                if (phoneNumberService.checkPhoneNumbersExist(phoneNumberDTOs)) {
                    errors.rejectValue("phoneNumberDTOs", "phone.number.is.already.exists");
                }
            }
        }
    }
}
