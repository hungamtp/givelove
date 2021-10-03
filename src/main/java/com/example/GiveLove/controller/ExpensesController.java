package com.example.GiveLove.controller;

import com.example.GiveLove.dto.AddExpensesDTO;
import com.example.GiveLove.dto.ResponseDTO;
import com.example.GiveLove.responseCode.SuccessCode;
import com.example.GiveLove.services.ExpensesService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.zip.DataFormatException;

@RestController
@RequestMapping("expenses")
@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class ExpensesController {

    private ExpensesService expensesService;

    @PostMapping("/{campaignId}")
    @PreAuthorize("hasRole('Member')")
    public ResponseEntity<ResponseDTO> addExpenses(@RequestBody AddExpensesDTO expensesDTO ,
                                                   @PathVariable Long campaignId){
        ResponseDTO response = new ResponseDTO();
        expensesService.addExpenses(expensesDTO , campaignId);
        response.setData(expensesDTO);
        response.setSuccessCode(SuccessCode.ADD_EXPENSES_SUCCESS);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{campaignId}")
    @PreAuthorize("hasAnyRole('Donator' , 'Manager' , 'Admin')")
    public ResponseEntity<ResponseDTO> getAllExpenses(@PathVariable Long campaignId) throws DataFormatException {
        ResponseDTO response = new ResponseDTO();

        response.setData( expensesService.getAllExpenses(campaignId));
        response.setSuccessCode(SuccessCode.GET_ALL_EXPENSES_SUCCESS);
        return ResponseEntity.ok().body(response);
    }
}
