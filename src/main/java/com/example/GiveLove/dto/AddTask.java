package com.example.GiveLove.dto;

import com.example.GiveLove.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AddTask {

    String desc;
    String gift;
    LocalDate deadline;
    Task.Mission type;

}
