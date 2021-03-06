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

    @Query(value = "SELECT CASE WHEN count(members_id) > 0 THEN 1 ELSE 0 END " +
            "checkExist FROM users_campaign where members_id =?1 and campaign_id =?2 limit 1" , nativeQuery = true)
    int isInCampaign(Long memberId , Long campaignId);

    @Query(value = "SELECT CASE WHEN count(sponsors_id) > 0 THEN 1 ELSE 0 END checkExist " +
            "FROM users_campaigns where sponsors_id =?1 and campaigns_id =?2 limit 1" , nativeQuery = true)
    int isDonatorInCampaign(Long donatorId , Long campaignId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM users_campaign  WHERE members_id = ?1 AND campaign_id  =?2" , nativeQuery = true)
    void deleteUserFromCampaign(Long memberId , Long campaignId);
    //delete from users_campaigns  where sponsors_id = 1 and campaigns_id  =1

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM users_campaigns  WHERE sponsors_id = ?1 AND campaigns_id  =?2" , nativeQuery = true)
    void deleteDonatorFromCampaign(Long donatorId , Long campaignId);

    @Query(value = "select id from campaign c order by id desc limit 1" , nativeQuery = true)
    Long getLatestId();



}
