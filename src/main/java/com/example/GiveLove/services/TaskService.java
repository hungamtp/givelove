package com.example.GiveLove.services;

import com.example.GiveLove.dto.TaskDTO;
import com.example.GiveLove.entity.Task;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TaskService {

    List<TaskDTO> getAllTask(Long campaignId);
    TaskDTO addTask(String task , Long campaignId);
}
