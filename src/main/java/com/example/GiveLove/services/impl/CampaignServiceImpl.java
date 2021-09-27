package com.example.GiveLove.services.impl;

import com.example.GiveLove.converter.CampaignConverter;
import com.example.GiveLove.dto.PageDTO;
import com.example.GiveLove.entity.Campaign;
import com.example.GiveLove.repository.CampaignRepository;
import com.example.GiveLove.repository.UsersRepository;
import com.example.GiveLove.responseCode.ErrorCode;
import com.example.GiveLove.services.CampaignService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;



@Service
@AllArgsConstructor
public class CampaignServiceImpl implements CampaignService {

    private CampaignRepository campaignRepository;

    private UsersRepository usersRepository;

    private CampaignConverter campaignConverter;

    public PageDTO getAllCampaign(Pageable pageable , Specification specification){

        try{
            Page<Campaign> campaigns = campaignRepository.findAll(specification , pageable);

            return campaignConverter.pageToPageDTO(campaigns);
        }catch (Exception ex){
            throw new DataAccessResourceFailureException(ErrorCode.GET_CAMPAIGN_FAIL);
        }

    }

    public void addMemberToCampaign (Long memberId , Long campaignId ){

        usersRepository.findById(memberId).orElseThrow(() ->{
           throw  new IllegalStateException(ErrorCode.USER_NOT_FOUND);
        });


        campaignRepository.findById(campaignId).orElseThrow(() ->{
           throw new IllegalStateException(ErrorCode.CAMPAIGN_NOT_FOUND);
        });


        campaignRepository.addMemberToCampaign(memberId , campaignId);
    }

    public void addDonatorToCampaign (Long donatorId , Long campaignId ){

        usersRepository.findById(donatorId).orElseThrow(() ->{
            throw  new IllegalStateException(ErrorCode.USER_NOT_FOUND);
        });


        campaignRepository.findById(campaignId).orElseThrow(() ->{
            throw new IllegalStateException(ErrorCode.CAMPAIGN_NOT_FOUND);
        });


        campaignRepository.addMemberToCampaign(donatorId , campaignId);
    }


}
