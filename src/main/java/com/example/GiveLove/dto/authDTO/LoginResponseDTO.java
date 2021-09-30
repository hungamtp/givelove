package com.example.GiveLove.dto.authDTO;

import com.example.GiveLove.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponseDTO {
    private String jwt;
    private String username;
    private Long userId;
    private String role;
    private String avatar;
    private Users.Gender gender;
    private String phone;
    private String  fullName;
    private String email;
    private String address;
    private LocalDate dob;

}
