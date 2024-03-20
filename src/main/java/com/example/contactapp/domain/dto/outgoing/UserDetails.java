package com.example.contactapp.domain.dto.outgoing;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class UserDetails {
    private String username;

    private String name;

    private String mothersName;

    private String birthDate;

    private String tajNumber;

    private String taxNumber;

    private String email;

    private List<PhoneNumberDetails> phoneNumbers;

    private List<AddressDetails> addresses;
}
