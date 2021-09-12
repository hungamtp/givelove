package com.example.GiveLove.controller;


import com.example.GiveLove.dto.ResponseDTO;
import com.example.GiveLove.dto.authDTO.LoginDTO;
import com.example.GiveLove.dto.authDTO.LoginResponseDTO;
import com.example.GiveLove.dto.authDTO.SignupDTO;
import com.example.GiveLove.entity.Role;
import com.example.GiveLove.entity.Users;
import com.example.GiveLove.responseCode.ErrorCode;
import com.example.GiveLove.responseCode.SuccessCode;
import com.example.GiveLove.security.jwt.JwtConfig;
import com.example.GiveLove.services.UserService;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Date;

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

    @PostMapping("login")
    public ResponseEntity<ResponseDTO> login(@Valid @RequestBody LoginDTO user) {

        ResponseDTO response = new ResponseDTO();


        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user.getUsername(),
                user.getPassword()
        );
        if(authentication.isAuthenticated()){
            String token = Jwts.builder()
                    .setSubject(authentication.getName())
                    .claim("authorities", authentication.getAuthorities())
                    .setIssuedAt(new Date())
                    .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(jwtConfig.getTokenExpirationAfterDays())))
                    .signWith(secretKey)
                    .compact();

            String defaultAvatar = "https://firebasestorage.googleapis.com/v0/b/timer-34f5a.appspot.com/"+
                    "o/avatar%2Favatar%20default.png?alt=media&token=b12a3df6-93f5-4662-8628-e34c94817c9f";
            Users customer = userService.findByUsername(user.getUsername());

            LoginResponseDTO loginResponse = LoginResponseDTO.builder()
                    .avatar(customer.getAvatar() == null || customer.getAvatar().equals("")
                            ? defaultAvatar : customer.getAvatar())
                    .username(customer.getUsername())
                    .role(customer.getRole().getName())
                    .jwt("Bearer " + token)
                    .userId(customer.getId())
                    .build();

            response.setData(loginResponse);
            response.setSuccessCode(SuccessCode.LOGIN_SUCCESS);
        }else{
            response.setErrorCode(ErrorCode.LOGIN_FAIL);
        }
        return ResponseEntity.ok().body(response);

    }
}
