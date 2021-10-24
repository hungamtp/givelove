package com.example.GiveLove.repository;

import com.example.GiveLove.entity.SubTask;
import com.example.GiveLove.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubTaskRepository extends JpaRepository<SubTask , Long> {

    @Query(value = "SELECT DISTINCT * FROM sub_task st " +
            "inner join task t ON t.id = st.task_id " +
            "WHERE t.campaign_id  =?1" , nativeQuery = true)
    List<SubTask> findALl(Long campaignId);
}
