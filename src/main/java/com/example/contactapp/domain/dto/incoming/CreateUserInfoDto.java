package com.example.contactapp.domain.dto.incoming;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class CreateUserInfoDto {

    private String username;

    private String name;

    private String mothersName;

    private LocalDate birthDate;

    private String tajNumber;

    private String taxNumber;

    private String email;

    private List<CreateAddressDto> addresses;

    private List<CreatePhoneNumberDto> phoneNumbers;

}
