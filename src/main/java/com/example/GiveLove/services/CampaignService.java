package com.example.GiveLove.services;

import com.example.GiveLove.dto.AddCampaignDTO;
import com.example.GiveLove.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface CampaignService {

    PageDTO getAllCampaign(Pageable pageable , Specification specification);

    void addMemberToCampaign (Long memberId , Long campaignId );

    void addDonatorToCampaign (Long donatorId , Long campaignId );
    void addCampaign(AddCampaignDTO campaignDTO , Long managerId);
    void deleteUserFromCampaign(Long memberId , Long campaignId);
}
