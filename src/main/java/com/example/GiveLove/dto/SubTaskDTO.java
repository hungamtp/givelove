package com.example.GiveLove.dto;

import com.example.GiveLove.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubTaskDTO {

    private Long id;
    private float quantity;
    private String description;
    private String image;
    private LocalDate createdDate;
    private boolean isApproved;
    private String member;

}
