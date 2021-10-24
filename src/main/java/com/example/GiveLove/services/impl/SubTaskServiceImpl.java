package com.example.GiveLove.services.impl;

import com.example.GiveLove.converter.SubTaskConverter;
import com.example.GiveLove.dto.SubTaskDTO;
import com.example.GiveLove.entity.Campaign;
import com.example.GiveLove.entity.Task;
import com.example.GiveLove.repository.SubTaskRepository;
import com.example.GiveLove.services.SubTaskService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SubTaskServiceImpl implements SubTaskService {

    private SubTaskRepository subTaskRepository;
    private SubTaskConverter subTaskConverter;

    public List<SubTaskDTO> getAllSubTask(Long campaignId){

        return subTaskConverter.convertEntitiesToDtos( subTaskRepository.findALl(campaignId));


    }

}
