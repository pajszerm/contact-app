package com.example.contactapp.domain.dto.incoming;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreatePhoneNumberDto {

    private Long userId;

    private Integer phoneNumber;
}
