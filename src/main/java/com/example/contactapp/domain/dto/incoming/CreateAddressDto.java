package com.example.contactapp.domain.dto.incoming;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateAddressDto {

    private String cityName;

    private String streetName;

    private String houseNumber;

    private String postalCode;
}
