package com.example.GiveLove.controller;

import com.example.GiveLove.dto.AddExpensesDTO;
import com.example.GiveLove.dto.ResponseDTO;
import com.example.GiveLove.services.ExpensesService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("expenses")
@AllArgsConstructor
public class ExpensesController {

    private ExpensesService expensesService;

    @PostMapping("/{campaignId}")
    @PreAuthorize("hasRole('Member')")
    public ResponseEntity<ResponseDTO> addExpenses(@RequestBody AddExpensesDTO expensesDTO ,
                                                   @PathVariable Long campaignId){
        ResponseDTO response = new ResponseDTO();
        expensesService.addExpenses(expensesDTO , campaignId);
        response.setData(expensesDTO);
        return ResponseEntity.ok().body(response);
    }
}
