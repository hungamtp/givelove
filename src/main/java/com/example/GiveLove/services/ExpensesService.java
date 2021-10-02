package com.example.GiveLove.services;

import com.example.GiveLove.dto.AddExpensesDTO;

public interface ExpensesService {
    void addExpenses(AddExpensesDTO addExpensesDTO , Long campaignId);
}
