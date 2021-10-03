package com.example.GiveLove.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AddCampaignDTO {
    private String name;
    private String description;
    private String image;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long total;
    private String location;
}
