package com.example.GiveLove.services.impl;

import com.example.GiveLove.converter.TaskConverter;
import com.example.GiveLove.dto.TaskDTO;
import com.example.GiveLove.entity.Campaign;
import com.example.GiveLove.repository.TaskRepository;
import com.example.GiveLove.services.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
