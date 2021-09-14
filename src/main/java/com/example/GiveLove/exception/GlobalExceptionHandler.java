package com.example.GiveLove.exception;


import com.example.GiveLove.dto.ResponseDTO;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DataAccessResourceFailureException.class)
    public ResponseEntity convertEntityDTOException(DataAccessResourceFailureException ex, WebRequest request) {
        ResponseDTO errorResponse = new ResponseDTO(ex.getMessage(), null, null);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    

}
