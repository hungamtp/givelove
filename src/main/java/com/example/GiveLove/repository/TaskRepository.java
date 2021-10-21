package com.example.GiveLove.repository;

import com.example.GiveLove.entity.Campaign;
import com.example.GiveLove.entity.Task;
import com.example.GiveLove.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task , Long> {

    List<Task> findByCampaignAndMember (Campaign campaign ,Users member);
    List<Task> findByCampaign (Campaign campaign);
}
