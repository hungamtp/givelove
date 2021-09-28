package com.example.GiveLove.services;

import com.example.GiveLove.dto.TaskDTO;

import java.util.List;

public interface TaskService {

    List<TaskDTO> getAllTask(Long campaignId);
    TaskDTO addTask(String task , Long campaignId);
    void finishTask(Long taskId);
    void deleteTask(Long taskId);
}
