package com.example.GiveLove.services;

import com.example.GiveLove.dto.TaskDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TaskService {

    List<TaskDTO> getAllTask(Long campaignId);

}
