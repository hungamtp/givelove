package com.example.GiveLove.repository;

import com.example.GiveLove.entity.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign , Long> , JpaSpecificationExecutor<Campaign> {


    @Transactional
    @Modifying
    @Query(value = "INSERT INTO users_campaign VALUES (?1 , ?2)" , nativeQuery = true)
    void addMemberToCampaign(Long memberId ,Long campaignId);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO users_campaigns VALUES (?1 , ?2)" , nativeQuery = true)
    void addDonatorToCampaign(Long donatorId ,Long campaignId);


}
