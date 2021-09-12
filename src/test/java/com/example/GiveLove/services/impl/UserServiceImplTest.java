package com.example.GiveLove.services.impl;

import com.example.GiveLove.entity.Users;
import com.example.GiveLove.repository.UsersRepository;
import com.example.GiveLove.responseCode.ErrorCode;
import com.example.GiveLove.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @MockBean
    UsersRepository usersRepository;

    @Autowired
    UserService userService;

    @Test
    void contextLoads() {
    }

    @Test
    public void testSaveUserSuccess(){

        when(usersRepository.findByUsername("hung")).thenReturn(null);


        Users users = Users.builder()
                .username("hung")
                .password("hung")
                .email("hung")
                .build();
        when(usersRepository.save(users)).thenReturn(users);

        assertEquals(userService.save(users) , users);
    }

    @Test
    public void testSaveUserFail(){
        Users users = Users.builder()
                .username("hung")
                .password("hung")
                .email("hung")
                .build();

        when(usersRepository.findByUsername("hung")).thenReturn(users);


        assertThrows(DuplicateKeyException.class , () ->{
            userService.save(users);
        });
    }

}