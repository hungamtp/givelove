package com.example.GiveLove.dto.authDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

}
