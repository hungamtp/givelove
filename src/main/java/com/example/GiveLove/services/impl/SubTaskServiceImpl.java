package com.example.GiveLove.services.impl;

import com.example.GiveLove.converter.SubTaskConverter;
import com.example.GiveLove.dto.AddExpensesDTO;
import com.example.GiveLove.dto.SubTaskDTO;
import com.example.GiveLove.entity.SubTask;
import com.example.GiveLove.entity.Task;
import com.example.GiveLove.repository.SubTaskRepository;
import com.example.GiveLove.services.SubTaskService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class SubTaskServiceImpl implements SubTaskService {

    private SubTaskRepository subTaskRepository;
    private SubTaskConverter subTaskConverter;

    public List<SubTaskDTO> getAllSubTask(Long campaignId){

        return subTaskConverter.convertEntitiesToDtos( subTaskRepository.findALl(campaignId));


    }

    public void save(AddExpensesDTO addExpenses , Long taskId){

        SubTask subtask = SubTask.builder()
                        .task(Task.builder().id(taskId).build())
                                .createdDate(LocalDate.now())
                                        .description(addExpenses.getDescription())
                                                .quantity(addExpenses.getQuantity())
                                                        .money(addExpenses.getMoney())
                .image(addExpenses.getImage())
                .isApproved(false)
                                                                .build();
        subTaskRepository.save(subtask);
    }

}
