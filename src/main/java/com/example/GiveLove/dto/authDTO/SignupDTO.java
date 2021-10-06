package com.example.GiveLove.dto.authDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignupDTO {
    @NotBlank
    @Size(min = 6, max = 16)
    private String username;

    @NotBlank
    @Size(min = 8, max = 16)
    private String password;

    @Email
    @NotBlank
    private String email;

    private Long roleId;
}
