package com.example.GiveLove.controller;


import com.example.GiveLove.dto.ResponseDTO;
import com.example.GiveLove.entity.Campaign;
import com.example.GiveLove.repository.UsersRepository;
import com.example.GiveLove.repository.specification.CampaignSpecificationBuilder;
import com.example.GiveLove.responseCode.SuccessCode;
import com.example.GiveLove.services.CampaignService;
import com.example.GiveLove.services.UserService;
import lombok.AllArgsConstructor;
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
@RequestMapping("/campaign")
@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class CampaignController {

    private CampaignService campaignService;
    private UserService userService;

    @GetMapping
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<ResponseDTO> getAllCampaign(@RequestParam("pageNum") int page,
                                                      @RequestParam("pageSize") int size ,
                                                      @RequestParam("sort") String sort,
                                                      @RequestParam("search") String search  ){

        ResponseDTO response = new ResponseDTO();

        Pageable pageable = null;
        if (sort.contains("ASC")) {
            pageable = PageRequest.of(page, size, Sort.by(sort.replace("ASC", "")).ascending());
        } else {
            pageable = PageRequest.of(page, size, Sort.by(sort.replace("DES", "")).descending());
        }

        CampaignSpecificationBuilder builder = new CampaignSpecificationBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(search + ",");

        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }

        Specification<Campaign> spec = builder.build();

        response.setSuccessCode(SuccessCode.GET_CAMPAIGNS_SUCCESS);
        response.setData(campaignService.getAllCampaign(pageable , spec));
        return ResponseEntity.ok().body(response);
    }


    @GetMapping("/{userId}")
    @PreAuthorize("hasAnyRole('Member', 'Donator','Manager')")
    public ResponseEntity<ResponseDTO> getCampaignByForUser(@PathVariable Long userId){
        ResponseDTO response = new ResponseDTO();

        response.setData(userService.getCampaignByUser(userId));
        return ResponseEntity.ok().body(response);
    }


    @PostMapping("/addMember")
    @PreAuthorize("hasRole('Manager')")
    public ResponseEntity<ResponseDTO> addMember(@RequestParam(value = "memberId") Integer memberId ,
                                                 @RequestParam(value = "campaignId") Integer campaignId){
        ResponseDTO response = new ResponseDTO();

        campaignService.addMemberToCampaign(Integer.toUnsignedLong(memberId) ,Integer.toUnsignedLong(campaignId));

        response.setSuccessCode(SuccessCode.ADD_USER_SUCCESS);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/addDonator")
    @PreAuthorize("hasRole('Manager')")
    public ResponseEntity<ResponseDTO> addDonator(@RequestParam Long donatorId ,
                                                 @RequestParam Long campaignId){
        ResponseDTO response = new ResponseDTO();

        campaignService.addMemberToCampaign(donatorId , campaignId);

        response.setSuccessCode(SuccessCode.ADD_USER_SUCCESS);
        return ResponseEntity.ok().body(response);
    }


}
