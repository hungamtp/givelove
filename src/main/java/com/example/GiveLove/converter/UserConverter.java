package com.example.GiveLove.converter;

import com.example.GiveLove.dto.PageDTO;
import com.example.GiveLove.dto.UserDTO;
import com.example.GiveLove.dto.UserSearchDTO;
import com.example.GiveLove.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserConverter {

    public UserDTO convertEntityToDTO(Users user){

        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .role(user.getRole().getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .dob(user.getDateOfBirth())
                .address(user.getAddress())
                .build();

    }
    public UserSearchDTO convertEntityToSearchDTO(Users user){

        return UserSearchDTO.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .avatar(user.getAvatar())
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
