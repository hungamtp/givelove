package com.example.GiveLove.converter;

import com.example.GiveLove.dto.CampaignDTO;
import com.example.GiveLove.dto.PageDTO;
import com.example.GiveLove.entity.Campaign;
import com.example.GiveLove.entity.ExpensesBlock;
import com.example.GiveLove.entity.TransactionBlock;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CampaignConverter {


    public CampaignDTO convertEntityToDTO(Campaign campaign){
        long totalTransactionMoney =0;
        long totalExpensesMoney =0;

        if(campaign.getTransactions().size() != 0){
            totalTransactionMoney = campaign.getTransactions()
                    .stream().mapToLong(TransactionBlock::getMoney)
                    .sum();
        }

        if(campaign.getExpenses().size() != 0){
            totalExpensesMoney = campaign.getExpenses()
                    .stream().mapToLong(ExpensesBlock::getMoney)
                    .sum();
        }

        return CampaignDTO.builder()
                .id(campaign.getId())
                .name(campaign.getName())
                .description(campaign.getDescription())
                .image(campaign.getDescription())
                .endDate(campaign.getEndDate().toLocalDate())
                .startDate(campaign.getStartDate().toLocalDate())
                .location(campaign.getLocation())
                .totalExpenses(totalExpensesMoney)
                .remainMoney(totalExpensesMoney - totalExpensesMoney)
                .total(totalTransactionMoney)
                .build();
    }

    public PageDTO pageToPageDTO (Page<Campaign> page){

        var campaigns = page.stream()
                .map(campaign -> convertEntityToDTO(campaign))
                .collect(Collectors.toList());

        return PageDTO.builder()
                .totalPage(page.getTotalPages())
                .totalElement(page.getTotalElements())
                .data(campaigns)
                .build();
    }
}
