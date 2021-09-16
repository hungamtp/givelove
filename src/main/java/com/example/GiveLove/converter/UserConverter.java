package com.example.GiveLove.converter;

import com.example.GiveLove.dto.PageDTO;
import com.example.GiveLove.dto.UserDTO;
import com.example.GiveLove.entity.TransactionBlock;
import com.example.GiveLove.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserConverter {

    public UserDTO convertEntityToDTO(Users user){

        Long totalDonateMoney = user.getTransactions()
                .stream()
                .mapToLong(TransactionBlock::getMoney)
                .sum();

        return UserDTO.builder()
                .id(user.getId())
                .money(totalDonateMoney)
                .username(user.getUsername())
                .role(user.getRole().getName())
                .email(user.getEmail())
                .build();

    }

    public PageDTO convertPageToDTO(Page<Users> users){


        var userList  = users.stream()
                .map((user) -> convertEntityToDTO(user))
                .collect(Collectors.toList());

        return PageDTO.builder()
                .totalPage(users.getTotalPages())
                .totalElement(users.getTotalElements())
                .data(userList)
                .build();

    }

}
