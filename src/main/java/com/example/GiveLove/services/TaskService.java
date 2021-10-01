package com.example.GiveLove.services;

import com.example.GiveLove.dto.AddTask;
import com.example.GiveLove.dto.TaskDTO;

import java.util.List;

public interface TaskService {

    List<TaskDTO> getAllTask(Long campaignId);
    TaskDTO addTask(AddTask task , Long campaignId);
    void finishTask(Long taskId);
    void deleteTask(Long taskId);
}
