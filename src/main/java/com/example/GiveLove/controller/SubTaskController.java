package com.example.GiveLove.controller;


import com.example.GiveLove.dto.AddExpensesDTO;
import com.example.GiveLove.dto.ResponseDTO;
import com.example.GiveLove.responseCode.SuccessCode;
import com.example.GiveLove.services.SubTaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("subtask")
@AllArgsConstructor
public class SubTaskController {

    private SubTaskService subTaskService;

    @GetMapping("/{campaignId}")
    public ResponseEntity<ResponseDTO> getAllSubTask(@PathVariable Long campaignId){
        ResponseDTO response = new ResponseDTO();

        response.setData(subTaskService.getAllSubTask(campaignId));

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/{taskId}")
    public ResponseEntity<ResponseDTO> addSubTask(@PathVariable Long taskId , @RequestBody AddExpensesDTO expensesDTO){
        ResponseDTO response = new ResponseDTO();

        subTaskService.save(expensesDTO , taskId);
        response.setSuccessCode(SuccessCode.ADD_SUB_TASK_SUCCESS);

        return ResponseEntity.ok().body(response);
    }
}
