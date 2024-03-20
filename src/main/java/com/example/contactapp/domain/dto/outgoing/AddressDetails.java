package com.example.contactapp.domain.dto.outgoing;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressDetails {
    private String cityName;

    private String streetName;

    private String houseNumber;

    private String postalCode;
}
