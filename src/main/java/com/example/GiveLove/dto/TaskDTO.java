package com.example.GiveLove.dto;

import com.example.GiveLove.entity.Task;
import lombok.*;

import java.time.LocalDate;


@Builder
@Data
public class TaskDTO {

    private Long id;
    private int quantity;
    private String description;
    private String gift;
    private Task.Mission type;
    private LocalDate createdDate;
    private LocalDate deadline;
    private boolean status;
}
