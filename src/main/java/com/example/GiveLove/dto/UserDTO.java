package com.example.GiveLove.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String username;
    private String fullName;
    private String email;
    private String phone;
    private String role;
    private String address;
    private LocalDate dob;
}
