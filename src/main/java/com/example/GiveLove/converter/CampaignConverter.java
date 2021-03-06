package com.example.GiveLove.converter;

import com.example.GiveLove.dto.CampaignDTO;
import com.example.GiveLove.dto.PageDTO;
import com.example.GiveLove.entity.Campaign;
import com.example.GiveLove.entity.ExpensesBlock;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CampaignConverter {


    public CampaignDTO convertEntityToDTO(Campaign campaign){
        long totalExpensesMoney =0;

        if(campaign.getExpenses().size() != 0){
            totalExpensesMoney = campaign.getExpenses()
                    .stream().mapToLong(ExpensesBlock::getMoney)
                    .sum();
        }

        return CampaignDTO.builder()
                .id(campaign.getId())
                .name(campaign.getName())
                .description(campaign.getDescription())
                .image(campaign.getImage())
                .endDate(campaign.getEndDate().toLocalDate())
                .startDate(campaign.getStartDate().toLocalDate())
                .location(campaign.getLocation())
                .totalExpenses(totalExpensesMoney)
                .total(campaign.getMoney())
                .state(campaign.getState())
                .manager(campaign.getSecretaries().getFullName())
                .totalMember(campaign.getMembers().size())
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
