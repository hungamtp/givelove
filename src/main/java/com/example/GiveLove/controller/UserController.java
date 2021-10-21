package com.example.GiveLove.controller;


import com.example.GiveLove.converter.UserConverter;
import com.example.GiveLove.dto.ResponseDTO;
import com.example.GiveLove.dto.UpdateUserDTO;
import com.example.GiveLove.entity.Users;
import com.example.GiveLove.repository.specification.UserSpecificationBuilder;
import com.example.GiveLove.responseCode.ErrorCode;
import com.example.GiveLove.responseCode.SuccessCode;
import com.example.GiveLove.services.UserService;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.DataFormatException;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    private UserConverter userConverter;
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
    public ResponseEntity<ResponseDTO> updateRole(@PathVariable Long userId , @RequestParam Long roleId) {
        ResponseDTO response = new ResponseDTO();
        userService.updateRole(userId , roleId);
        response.setSuccessCode(SuccessCode.UPDATE_ROLE_SUCCESS);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/search")
    @PreAuthorize(("hasRole('Manager')"))
    public ResponseEntity<ResponseDTO> getUserByUsername(@RequestParam String fullName ,
                                                         @RequestParam Long roleId) throws DataFormatException {

        ResponseDTO response = new ResponseDTO();
        if(roleId <3 || roleId > 4){
            throw new DataFormatException(ErrorCode.ROLE_NOT_ALLOWED);
        }
        response.setData( userService.findByFullname(fullName , roleId));
        response.setSuccessCode(SuccessCode.GET_USER_SUCCESS);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/update/{userId}")
    @PreAuthorize("hasAnyRole('Manager' , 'Member' , 'Donator')")
    public ResponseEntity<ResponseDTO> updateUser(@PathVariable Long userId ,
                                                  @RequestBody @Valid UpdateUserDTO userDTO) throws DataFormatException {

        ResponseDTO response = new ResponseDTO();
        response.setData(userDTO);
        userService.updateUserProfile(userId , userDTO);
        response.setSuccessCode(SuccessCode.UPDATE_USER_SUCCESS);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("usersInCampaign/{campaignId}")
    @PreAuthorize("hasAnyRole('Manager' , 'Admin' , 'Donator' )")
    public ResponseEntity<ResponseDTO> getAllUserInCampaign(@PathVariable Long campaignId) throws DataFormatException {

        ResponseDTO response = new ResponseDTO();
        response.setData(userService.getAllUserInCampaign(campaignId));
        response.setSuccessCode(SuccessCode.GET_USER_SUCCESS);
        return ResponseEntity.ok().body(response);
    }
    @GetMapping("donatorInCampaign/{campaignId}")
    @PreAuthorize("hasRole('Manager' )")
    public ResponseEntity<ResponseDTO> getAllDonatorInCampaign(@PathVariable Long campaignId) throws DataFormatException {

        ResponseDTO response = new ResponseDTO();
        response.setData(userService.getAllDonatorInCampaign(campaignId));
        response.setSuccessCode(SuccessCode.GET_DONATOR_SUCCESS);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasAnyRole('Donator' , 'Manager' , 'Admin' , 'Member')")
    public ResponseEntity<ResponseDTO> getUserById(@PathVariable Long userId)  {

        ResponseDTO response = new ResponseDTO();
        response.setData(userService.getUserById(userId));
        response.setSuccessCode(SuccessCode.GET_USER_SUCCESS);
        return ResponseEntity.ok().body(response);
    }

}
