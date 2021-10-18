package com.example.GiveLove.services;

import com.example.GiveLove.dto.AddExpensesDTO;
import com.example.GiveLove.dto.ExpensesDTO;

import java.util.List;
import java.util.zip.DataFormatException;

public interface ExpensesService {
    void addExpenses(AddExpensesDTO addExpensesDTO , Long campaignId) throws DataFormatException;
    List<ExpensesDTO> getAllExpenses(Long campaignId) throws DataFormatException;
}
