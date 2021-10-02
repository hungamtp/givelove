package com.example.GiveLove.repository;

import com.example.GiveLove.entity.ExpensesBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpensesRepository extends JpaRepository<ExpensesBlock , Long> {

    @Query(value = "SELECT hash FROM expenses_block WHERE campaign_id = ?1 order by `date` desc limit 1"
            , nativeQuery = true)
    String getLatestHash(Long campaignId);
}
