package com.example.GiveLove.repository;

import com.example.GiveLove.entity.Campaign;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CampaignRepositoryTest {

    @Autowired
    CampaignRepository campaignRepository;

    @Test
    public void getCam(){
        Pageable pageable = PageRequest.of(0 , 10);

        Specification<Campaign> specification = new Specification<Campaign>() {
            @Override
            public Predicate toPredicate(Root<Campaign> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {
                return builder.like(
                        builder.lower(root.<String>get("name" )) ,"con");


            }
        };

        assertEquals(campaignRepository.findAll( pageable).getTotalElements() , 1);

    }

}