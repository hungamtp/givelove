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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@CrossOrigin(origins = "*", maxAge = 3600)
@AllArgsConstructor
public class AuthController {

    private AuthenticationManager authenticationManager;
    private JwtConfig jwtConfig;
    private SecretKey secretKey;
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;



    @Operation(summary = "Create new account", description = "", tags = { "All" })
    @ApiResponses(value = { @ApiResponse(responseCode = "2xx", description = "Successfull"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error") })
    @PostMapping("signup")
    public ResponseEntity<ResponseDTO> signUp(@Valid @RequestBody SignupDTO user) {

        ResponseDTO response = new ResponseDTO();

        Users customer = Users.builder()
                .username(user.getUsername())
                .password("{bcrypt}" + passwordEncoder.encode(user.getPassword()))
                .email(user.getEmail())
                .role(Role.builder().id(2L).build())
                .build();

        response.setData(userService.save(customer));
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

        Authentication authenticate = authenticationManager.authenticate(authentication);

        if(authenticate.isAuthenticated()){
            String token = Jwts.builder()
                    .setSubject(authentication.getName())
                    .claim("authorities", authenticate.getAuthorities())
                    .setIssuedAt(new Date())
                    .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(jwtConfig.getTokenExpirationAfterDays())))
                    .signWith(secretKey)
                    .compact();

            Users customer = userService.findByUsername(user.getUsername());

            LoginResponseDTO loginResponse = LoginResponseDTO.builder()
                    .avatar(customer.getAvatar() == null || customer.getAvatar().equals("")
                            ? null : customer.getAvatar())
                    .username(customer.getUsername())
                    .role(customer.getRole().getName())
                    .jwt("Bearer " + token)
                    .userId(customer.getId())
                    .gender(customer.getGender())
                    .phone(customer.getPhone())
                    .fullName(customer.getFullName())
                    .email(customer.getEmail())
                    .address(customer.getAddress())
                    .dob(customer.getDateOfBirth())
                    .build();

            response.setData(loginResponse);
            response.setSuccessCode(SuccessCode.LOGIN_SUCCESS);
        }else{
            response.setErrorCode(ErrorCode.LOGIN_FAIL);
        }
        return ResponseEntity.ok().body(response);

    }
}
