package com.example.GiveLove.services.impl;

import com.example.GiveLove.converter.ExpensesConverter;
import com.example.GiveLove.dto.AddExpensesDTO;
import com.example.GiveLove.dto.ExpensesDTO;
import com.example.GiveLove.entity.Campaign;
import com.example.GiveLove.entity.ExpensesBlock;
import com.example.GiveLove.repository.ExpensesRepository;
import com.example.GiveLove.responseCode.ErrorCode;
import com.example.GiveLove.services.ExpensesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;

@Service
@AllArgsConstructor
public class ExpensesServiceImpl implements ExpensesService {

    private static final int PREFIX = 1;

    private ExpensesRepository expensesRepository;
    private ExpensesConverter expensesConverter;

    public void addExpenses(AddExpensesDTO addExpensesDTO , Long campaignId){
        ExpensesBlock expensesBlock = ExpensesBlock.builder()
                .description(addExpensesDTO.getDescription())
                .money(addExpensesDTO.getMoney())
                .date(LocalDateTime.now())
                .image(addExpensesDTO.getImage())
                .campaign(Campaign.builder().id(campaignId).build())
                .build();

        expensesBlock.setHash(expensesBlock.calculateBlockHash());

        if(expensesRepository.findAll().size() == 0){
            expensesBlock.setPreviousHash("0");
        }
        else{
            // get latest block
            expensesBlock.setPreviousHash(expensesRepository.getLatestHash(campaignId));

        }

        expensesBlock.mineBlock(1);

        expensesRepository.save(expensesBlock);

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