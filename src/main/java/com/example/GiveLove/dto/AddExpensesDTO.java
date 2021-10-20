package com.example.GiveLove.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddExpensesDTO {
    Long money;
    String description;
    String image;
    String location;
    Long taskId;
    String unit;
    String giftname;
    float quantity;

}
