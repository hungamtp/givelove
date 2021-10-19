package com.example.GiveLove.converter;

import com.example.GiveLove.dto.ExpensesDTO;
import com.example.GiveLove.entity.ExpensesBlock;
import org.springframework.stereotype.Component;

@Component
public class ExpensesConverter {

    public ExpensesDTO convertEntityToDTO(ExpensesBlock expenses){
        return ExpensesDTO.builder()
                .dateTime(expenses.getDate())
                .money(expenses.getMoney())
                .description(expenses.getDescription())
                .image(expenses.getImage())
                .location(expenses.getLocation())
                .build();
    }
}
