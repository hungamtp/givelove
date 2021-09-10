package com.example.GiveLove.controller;


import com.example.GiveLove.dto.ResponseDTO;
import com.example.GiveLove.dto.authDTO.SignupDTO;
import com.example.GiveLove.entity.Role;
import com.example.GiveLove.entity.Users;
import com.example.GiveLove.responseCode.SuccessCode;
import com.example.GiveLove.security.jwt.JwtConfig;
import com.example.GiveLove.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import javax.validation.Valid;

@RestController
@RequestMapping("auth/")
@CrossOrigin
@AllArgsConstructor
public class AuthController {

    private AuthenticationManager authenticationManager;
    private JwtConfig jwtConfig;
    private SecretKey secretKey;
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;



    @PostMapping("signup")
    public ResponseEntity<ResponseDTO> signUp(@Valid @RequestBody SignupDTO user) {

        ResponseDTO response = new ResponseDTO();

        Users customer = Users.builder()
                .username(user.getUsername())
                .password("{bcrypt}" + passwordEncoder.encode(user.getPassword()))
                .email(user.getEmail())
                .role(Role.builder().id(1L).build())
                .build();

        userService.save(customer);
        response.setSuccessCode(SuccessCode.SIGN_UP_SUCCESS);
        return ResponseEntity.ok().body(response);

    }
}
