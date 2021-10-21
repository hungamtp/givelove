package com.example.GiveLove.dto;

import com.example.GiveLove.entity.Task;
import lombok.*;

import java.time.LocalDate;


@Builder
@Data
public class TaskDTO {

    private Long id;
    private float quantity;
    private float quantityRemain;
    private String description;
    private String unit;
    private String gift;
    private String member;
    private Task.Mission type;
    private LocalDate createdDate;
    private LocalDate deadline;
    private boolean status;
}
