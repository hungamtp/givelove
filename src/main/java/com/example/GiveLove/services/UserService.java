package com.example.GiveLove.services;

import com.example.GiveLove.dto.PageDTO;
import com.example.GiveLove.entity.Users;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface UserService {

    Users save(Users users);

    Users findByUsername(String username);

    PageDTO getAllUser(Specification specification , Pageable pageable);

    void updateRole(Long userId);
}
