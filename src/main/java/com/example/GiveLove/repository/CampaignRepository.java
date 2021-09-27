package com.example.GiveLove.repository;

import com.example.GiveLove.entity.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign , Long> , JpaSpecificationExecutor<Campaign> {


    @Query(value = "INSERT INTO users_campaign VALUES (?1 , ?2)" , nativeQuery = true)
    void addMemberToCampaign(Long memberId ,Long campaignId);


}
