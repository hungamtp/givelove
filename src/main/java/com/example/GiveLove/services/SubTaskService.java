package com.example.GiveLove.services;

import com.example.GiveLove.dto.AddExpensesDTO;
import com.example.GiveLove.dto.SubTaskDTO;

import java.util.List;

public interface SubTaskService {
    List<SubTaskDTO> getAllSubTask(Long campaignId);
    void save(AddExpensesDTO addExpenses , Long taskId);
}
