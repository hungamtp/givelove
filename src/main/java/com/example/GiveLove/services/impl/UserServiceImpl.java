package com.example.GiveLove.services.impl;

import com.example.GiveLove.entity.Users;
import com.example.GiveLove.repository.UsersRepository;
import com.example.GiveLove.responseCode.ErrorCode;
import com.example.GiveLove.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepository usersRepository;


    @Override
    public Users save(Users users) {

        Optional<Users> user = Optional.ofNullable(usersRepository.findByUsername(users.getUsername()));

        if(user.isPresent()){
            throw new DuplicateKeyException(ErrorCode.USERNAME_NOT_AVAILABLE);
        }
        Users savedUser = usersRepository.save(users);
        return savedUser;
    }
}
