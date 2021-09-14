package com.example.GiveLove.repository.specification;

import com.example.GiveLove.entity.Campaign;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@AllArgsConstructor
public class CampaignSpecification implements Specification<Campaign> {

    private SearchCriteria criteria;

//    @Override
//    public Specification<Campaign> and(Specification<Campaign> other) {
//        return Specification.super.and(other);
//    }
//
//    @Override
//    public Specification<Campaign> or(Specification<Campaign> other) {
//        return Specification.super.or(other);
//    }

    @Override
    public Predicate toPredicate(Root<Campaign> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {
        if (criteria.getOperation().equalsIgnoreCase(">")) {
            return builder.greaterThanOrEqualTo(
                    root.<String>get(criteria.getKey()), criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase("<")) {
            return builder.lessThanOrEqualTo(
                    root.<String>get(criteria.getKey()), criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.like(builder.lower(root.<String>get(criteria.getKey()))
                        , "%" + ((String) criteria.getValue()).toLowerCase() + "%");
            } else {
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }
        return null;
    }
}
