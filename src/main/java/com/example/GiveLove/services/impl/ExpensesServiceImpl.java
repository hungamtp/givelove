package com.example.GiveLove.services.impl;

import com.example.GiveLove.converter.ExpensesConverter;
import com.example.GiveLove.dto.AddExpensesDTO;
import com.example.GiveLove.dto.ExpensesDTO;
import com.example.GiveLove.entity.Campaign;
import com.example.GiveLove.entity.ExpensesBlock;
import com.example.GiveLove.entity.SubTask;
import com.example.GiveLove.entity.Task;
import com.example.GiveLove.repository.ExpensesRepository;
import com.example.GiveLove.repository.SubTaskRepository;
import com.example.GiveLove.repository.TaskRepository;
import com.example.GiveLove.responseCode.ErrorCode;
import com.example.GiveLove.services.ExpensesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;

@Service
@AllArgsConstructor
public class ExpensesServiceImpl implements ExpensesService {

    private static final int PREFIX = 1;

    private ExpensesRepository expensesRepository;
    private SubTaskRepository subTaskRepository;
    private TaskRepository taskRepository;
    private ExpensesConverter expensesConverter;

    public void addExpenses(Long subtaskId , Long campaignId) throws DataFormatException {

        Optional<SubTask> subTaskOptional = subTaskRepository.findById(subtaskId);
        SubTask subTask = null;
        if(!subTaskOptional.isPresent()){
            throw new IllegalStateException(ErrorCode.TASK_NOT_FOUND);
        }else {
            subTask = subTaskOptional.get();



            ExpensesBlock expensesBlock = ExpensesBlock.builder()
                    .description(subTask.getDescription())
                    .money((long) subTask.getMoney())
                    .date(LocalDateTime.now())
                    .image(subTask.getImage())
                    .giftname(subTask.getTask().getGift())
                    .unit(subTask.getTask().getUnit())
                    .campaign(Campaign.builder().id(campaignId).build())
                    .build();

            expensesBlock.setHash(expensesBlock.calculateBlockHash());

            if (expensesRepository.findAllByCampaign(Campaign.builder().id(campaignId).build()).size() == 0) {
                expensesBlock.setPreviousHash("0");
            } else {
                // get latest block
                expensesBlock.setPreviousHash(expensesRepository.getLatestHash(campaignId));

            }

            expensesBlock.mineBlock(1);

            expensesRepository.save(expensesBlock);
            subTask.setApproved(true);
            subTaskRepository.save(subTask);
            var task = taskRepository.findById(subTask.getTask().getId()).get();

            if (task.getQuantityRemain() - subTask.getQuantity() == 0) {
                task.setQuantityRemain(task.getQuantityRemain() - subTask.getQuantity());
                task.setStatus(true);
                taskRepository.save(task);
            } else {
                task.setQuantityRemain(task.getQuantityRemain() - subTask.getQuantity());
                taskRepository.save(task);
            }
        }

    }

    public List<ExpensesDTO> getAllExpenses(Long campaignId) throws DataFormatException {

        List<ExpensesBlock> blocks = expensesRepository.findAllByCampaign(Campaign.builder().id(campaignId).build());
        List<ExpensesDTO> expensesList= blocks
                .stream()
                .map(expensesBlock -> expensesConverter.convertEntityToDTO(expensesBlock))
                .collect(Collectors.toList());


        for(var expenses : blocks){
           if (!expenses.getHash().equals(expenses.mineBlock(PREFIX))){
               throw new DataFormatException(ErrorCode.EXPENSES_INVALID);
           }
        }


        return expensesList;
    }
}
