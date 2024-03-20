package com.example.contactapp.domain.dto.outgoing;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserDetailsResponse {
    private List<UserDetails> users;
    private Integer pageIndex;
    private Integer pageSize;
    private Long length;
}
