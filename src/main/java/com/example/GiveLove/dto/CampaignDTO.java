package com.example.GiveLove.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CampaignDTO {
    private Long id;
    private String name;
    private String description;
    private String image;
    private String state;
    private Long totalExpenses;
    private Long total;
    private String location;
    private LocalDate startDate;
    private LocalDate endDate;

}
