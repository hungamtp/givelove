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
    private float money;
    private String description;
    private String giftname;
    private String image;
    private String location;
    private Task.Mission type;
    private LocalDate createdDate;
    private boolean isApproved;
    private String member;

}
