package com.example.GiveLove.dto;

import com.example.GiveLove.responseCode.ErrorCode;
import com.example.GiveLove.responseCode.SuccessCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDTO {
    private String successCode;
    private Object data;
    private String errorCode;
}
