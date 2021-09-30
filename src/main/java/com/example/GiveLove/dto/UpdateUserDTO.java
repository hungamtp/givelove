package com.example.GiveLove.dto;

import com.example.GiveLove.entity.Users;
import com.example.GiveLove.responseCode.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateUserDTO {
    @NotBlank(message = ErrorCode.FULL_NAME_BLANK)
    private String fullName;
    private LocalDate dob;
    private Users.Gender gender;
    @NotBlank(message = ErrorCode.PHONE_BLANK)
    private String phone;
    @NotBlank(message = ErrorCode.EMAIL_BLANK)
    private String email;
    @NotBlank(message = ErrorCode.ADDRESS_BLANK)
    private String address;
}
