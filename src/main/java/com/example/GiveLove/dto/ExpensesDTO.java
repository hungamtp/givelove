package com.example.GiveLove.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ExpensesDTO {
    private String description;
    private Long money;
    private LocalDateTime dateTime;
    private String image;
    private String location;
    private String unit;
    private String giftname;
}
