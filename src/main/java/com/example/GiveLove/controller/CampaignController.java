package com.example.GiveLove.controller;


import com.example.GiveLove.dto.ResponseDTO;
import com.example.GiveLove.repository.specification.CampaignSpecification;
import com.example.GiveLove.repository.specification.SearchCriteria;
import com.example.GiveLove.responseCode.SuccessCode;
import com.example.GiveLove.services.CampaignService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/campaign")
@AllArgsConstructor
public class CampaignController {

    private CampaignService campaignService;

    @GetMapping
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<ResponseDTO> getAllCampaign(@Param("name") String name ,
                                                      @Param("page") int page,
                                                      @Param("size") int size ,
                                                      @Param("sort") String sort ){

        ResponseDTO response = new ResponseDTO();

        Pageable pageable = PageRequest.of(page , size);

        SearchCriteria searchCriteria = new SearchCriteria("name" , ":" , name);

        CampaignSpecification specification = new CampaignSpecification(searchCriteria);

        response.setSuccessCode(SuccessCode.GET_CAMPAIGNS_SUCCESS);
        response.setData(campaignService.getAllCampaign(pageable , specification));
        return ResponseEntity.ok().body(response);
    }
}
