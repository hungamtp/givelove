package com.example.GiveLove.services.impl;

import com.example.GiveLove.converter.TaskConverter;
import com.example.GiveLove.dto.AddTask;
import com.example.GiveLove.dto.TaskDTO;
import com.example.GiveLove.entity.Campaign;
import com.example.GiveLove.entity.Task;
import com.example.GiveLove.entity.Users;
import com.example.GiveLove.repository.TaskRepository;
import com.example.GiveLove.repository.UsersRepository;
import com.example.GiveLove.responseCode.ErrorCode;
import com.example.GiveLove.services.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;
    private UsersRepository usersRepository;
    private TaskConverter taskConverter;

    @Override
    public List<TaskDTO> getAllTask(Long campaignId , String username) {

        Users user =   usersRepository.findByUsername(username);
        Campaign campaign = Campaign.builder().id(campaignId).build();
        return taskConverter.convertEntitiesToDTOs(taskRepository.findByCampaignAndMemberAndStatusIsFalse(campaign , user));
    }

    @Override
    public TaskDTO addTask(AddTask task  , Long campaignId) {
        Task addedTask = taskRepository.save(
                Task.builder()
                        .description(task.getDesc())
                        .deadline(task.getDeadline())
                        .createdDate(LocalDate.now())
                        .status(false)
                        .unit(task.getUnit())
                        .quantity(task.getQuantity())
                        .quantityRemain(task.getQuantityRemain())
                        .type(task.getType())
                        .gift(task.getGift())
                        .member(Users.builder().id(task.getMemberId()).build())
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
