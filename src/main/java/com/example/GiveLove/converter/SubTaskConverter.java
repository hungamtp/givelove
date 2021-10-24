package com.example.GiveLove.converter;

import com.example.GiveLove.dto.SubTaskDTO;
import com.example.GiveLove.entity.SubTask;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SubTaskConverter {


    public SubTaskDTO convertEntityToDto(SubTask subTask){
        return SubTaskDTO.builder()
                .id(subTask.getId())
                .quantity(subTask.getQuantity())
                .description(subTask.getDescription())
                .image(subTask.getImage())
                .createdDate(subTask.getCreatedDate())
                .isApproved(subTask.isApproved())
                .member(subTask.getTask().getMember().getFullName())
                .type(subTask.getTask().getType())
                .location(subTask.getTask().getCampaign().getLocation())
                .build();
    }

    public List<SubTaskDTO> convertEntitiesToDtos(List<SubTask> subTasks){
        return subTasks.stream()
                .map((subTask -> convertEntityToDto(subTask)))
                .collect(Collectors.toList());
    }
}
