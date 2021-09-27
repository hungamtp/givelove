package com.example.GiveLove.controller;


import com.example.GiveLove.dto.ResponseDTO;
import com.example.GiveLove.entity.Users;
import com.example.GiveLove.repository.specification.UserSpecificationBuilder;
import com.example.GiveLove.responseCode.SuccessCode;
import com.example.GiveLove.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<ResponseDTO> getAllUser(@RequestParam("pageNum") int page,
                                                  @RequestParam("pageSize") int size,
                                                  @RequestParam String sort,
                                                  @RequestParam String search) {
        ResponseDTO response = new ResponseDTO();

        Pageable pageable = null;
        if (sort.contains("ASC")) {
            pageable = PageRequest.of(page, size, Sort.by(sort.replace("ASC", "")).ascending());
        } else {
            pageable = PageRequest.of(page, size, Sort.by(sort.replace("DES", "")).descending());
        }

        UserSpecificationBuilder builder = new UserSpecificationBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(search + ",");

        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }

        Specification<Users> spec = builder.build();

        response.setData(userService.getAllUser(spec, pageable));
        response.setSuccessCode(SuccessCode.GET_ALL_USER_SUCCESS);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{userId}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<ResponseDTO> updateRole(@PathVariable Long userId) {
        ResponseDTO response = new ResponseDTO();
        userService.updateRole(userId);
        response.setSuccessCode(SuccessCode.UPDATE_ROLE_SUCCESS);
        return ResponseEntity.ok().body(response);
    }


}
