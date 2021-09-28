package com.example.GiveLove.dto;

import lombok.*;

import java.time.LocalDate;


@Builder
public class TaskDTO {

    private Long id;
    private String description;
    private boolean isDone;
    private LocalDate createdDate;
}
