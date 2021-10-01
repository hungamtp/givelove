package com.example.GiveLove.controller;


import com.example.GiveLove.dto.AddTask;
import com.example.GiveLove.dto.ResponseDTO;
import com.example.GiveLove.responseCode.SuccessCode;
import com.example.GiveLove.services.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class TaskController {

    private TaskService taskService;

    @GetMapping
    @PreAuthorize("hasAnyRole('Manager','Member')")
    public ResponseEntity<ResponseDTO> getAllTask(@RequestParam Long campaignId){
        ResponseDTO response = new ResponseDTO();
        response.setData(taskService.getAllTask(campaignId));
        response.setSuccessCode(SuccessCode.GET_ALL_CAMPAIGN_SUCCESS);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/{campaignId}")
    @PreAuthorize("hasRole('Manager')")
    public ResponseEntity<ResponseDTO> addTask(@RequestBody AddTask task , @PathVariable Long campaignId){
        ResponseDTO response = new ResponseDTO();

        response.setData(taskService.addTask(task , campaignId));
        response.setSuccessCode(SuccessCode.ADD_TASK_SUCCESS);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/finish/{taskId}")
    @PreAuthorize("hasRole('Manager')")
    public ResponseEntity<ResponseDTO> finishTask(@PathVariable Long taskId ){
        ResponseDTO response = new ResponseDTO();

        taskService.finishTask(taskId );
        response.setSuccessCode(SuccessCode.UPDATE_TASK_SUCCESS);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{taskId}")
    @PreAuthorize("hasRole('Manager')")
    public ResponseEntity<ResponseDTO> deleteTask(@PathVariable Long taskId ){
        ResponseDTO response = new ResponseDTO();

        taskService.deleteTask(taskId);
        response.setSuccessCode(SuccessCode.DELETE_TASK_SUCCESS);
        return ResponseEntity.ok().body(response);
    }
}
