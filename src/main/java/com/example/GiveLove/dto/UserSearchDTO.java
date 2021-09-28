package com.example.GiveLove.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserSearchDTO {
    private Long userId;
    private String username;
    private String avatar;
}
