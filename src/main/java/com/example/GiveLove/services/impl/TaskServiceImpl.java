package com.example.GiveLove.services.impl;

import com.example.GiveLove.converter.TaskConverter;
import com.example.GiveLove.dto.TaskDTO;
import com.example.GiveLove.entity.Campaign;
import com.example.GiveLove.entity.Task;
import com.example.GiveLove.repository.TaskRepository;
import com.example.GiveLove.responseCode.ErrorCode;
import com.example.GiveLove.services.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;
    private TaskConverter taskConverter;

    @Override
    public List<TaskDTO> getAllTask(Long campaignId) {
        return taskConverter.convertEntitiesToDTOs(
                taskRepository.findByCampaign(Campaign.builder().id(campaignId).build()));
    }

    @Override
    public TaskDTO addTask(String task  , Long campaignId) {
        Task addedTask = taskRepository.save(
                Task.builder()
                        .description(task)
                        .createdDate(LocalDate.now())
                        .status(false)
                        .campaign(Campaign.builder().id(campaignId).build())
                        .build()
        );
        return taskConverter.convertEntityToDTO( addedTask);
    }

    @Override
    public void finishTask(Long taskId) {

        taskRepository.save(Task.builder()
                .id(taskId)
                .status(true)
                .build());
    }

    public void deleteTask(Long taskId){

        taskRepository.findById(taskId).orElseThrow(() ->{
            throw new IllegalStateException(ErrorCode.TASK_NOT_FOUND);
        });

        taskRepository.deleteById(taskId);
    }


}
