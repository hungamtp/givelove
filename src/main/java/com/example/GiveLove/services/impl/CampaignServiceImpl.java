package com.example.GiveLove.services.impl;

import com.example.GiveLove.converter.CampaignConverter;
import com.example.GiveLove.dto.AddCampaignDTO;
import com.example.GiveLove.dto.CampaignDTO;
import com.example.GiveLove.dto.PageDTO;
import com.example.GiveLove.entity.Campaign;
import com.example.GiveLove.entity.Users;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


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

        if(campaignRepository.isInCampaign(memberId, campaignId)==1){
            throw new IllegalStateException(ErrorCode.MEMBER_IS_IN_CAMPAIGN);
        }


        campaignRepository.addMemberToCampaign(memberId , campaignId);
    }

    public void addDonatorToCampaign (Long donatorId , Long campaignId ){

        usersRepository.findById(donatorId).orElseThrow(() ->{
            throw  new IllegalStateException(ErrorCode.USER_NOT_FOUND);
        });


        campaignRepository.findById(campaignId).orElseThrow(() ->{
            throw new IllegalStateException(ErrorCode.CAMPAIGN_NOT_FOUND);
        });

        if(campaignRepository.isDonatorInCampaign(donatorId , campaignId)==1){
            throw new IllegalStateException(ErrorCode.MEMBER_IS_IN_CAMPAIGN);
        }

        campaignRepository.addDonatorToCampaign(donatorId , campaignId);
    }

    public CampaignDTO addCampaign(AddCampaignDTO campaignDTO , Long managerId){


        Campaign campaign = Campaign.builder()
                .image(campaignDTO.getImage())
                .name(campaignDTO.getName())
                .description(campaignDTO.getDescription())
                .endDate(campaignDTO.getEndDate().atTime(0,0))
                .startDate(campaignDTO.getStartDate().atTime(0 ,0))
                .secretaries(Users.builder().id(managerId).build())
                .money(campaignDTO.getTotal())
                .location(campaignDTO.getLocation())
                .state("true")
                .build();

        campaignRepository.save(campaign);

      return CampaignDTO.builder()
              .id(campaignRepository.getLatestId())
              .name(campaignDTO.getName())
              .description(campaign.getDescription())
              .image(campaign.getImage())
              .startDate(campaignDTO.getStartDate())
              .endDate(campaignDTO.getEndDate())
              .total(campaignDTO.getTotal())
              .location(campaignDTO.getLocation())
              .totalMember(0)
              .manager(usersRepository.findById(managerId).get().getFullName())
              .state("Still")
              .totalExpenses(0L)
              .build();
    }

    public void deleteUserFromCampaign(Long memberId , Long campaignId){

        usersRepository.findById(memberId).orElseThrow(() ->{
            throw  new IllegalStateException(ErrorCode.USER_NOT_FOUND);
        });


        campaignRepository.findById(campaignId).orElseThrow(() ->{
            throw new IllegalStateException(ErrorCode.CAMPAIGN_NOT_FOUND);
        });

        campaignRepository.deleteUserFromCampaign(memberId , campaignId);
    }

    public void deleteDonatorFromCampaign(Long donatorId , Long campaignId){

        usersRepository.findById(donatorId).orElseThrow(() ->{
            throw  new IllegalStateException(ErrorCode.USER_NOT_FOUND);
        });


        campaignRepository.findById(campaignId).orElseThrow(() ->{
            throw new IllegalStateException(ErrorCode.CAMPAIGN_NOT_FOUND);
        });

        campaignRepository.deleteDonatorFromCampaign(donatorId , campaignId);
    }


}
