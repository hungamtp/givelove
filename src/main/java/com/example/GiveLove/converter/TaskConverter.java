package com.example.GiveLove.converter;

import com.example.GiveLove.dto.TaskDTO;
import com.example.GiveLove.entity.Task;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskConverter {

    public TaskDTO convertEntityToDTO(Task task){

        return TaskDTO.builder()
                .id(task.getId())
                .description(task.getDescription())
                .createdDate(task.getCreatedDate())
                .status(task.isStatus())
                .gift(task.getGift())
                .type(task.getType())
                .quantity(task.getQuantity())
                .deadline(task.getDeadline())
                .build();
    }

    public List<TaskDTO> convertEntitiesToDTOs(List<Task> tasks){
        return tasks.stream()
                .map(task -> convertEntityToDTO(task))
                .collect(Collectors.toList());
    }
}
