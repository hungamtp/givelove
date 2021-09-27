package com.example.GiveLove.repository.specification;

import com.example.GiveLove.entity.Campaign;
import com.example.GiveLove.entity.Users;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CampaignSpecificationBuilder {

    private final List<SearchCriteria> params;

    public CampaignSpecificationBuilder() {
        params = new ArrayList<SearchCriteria>();
    }

    public CampaignSpecificationBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public Specification<Campaign> build() {
        if (params.size() == 0) {
            return null;
        }


        List<Specification> specs = params.stream()
                .map(UserSpecification::new)
                .collect(Collectors.toList());

        Specification result = specs.get(0);

        for (int i = 1; i < params.size(); i++) {

            result = Specification.where(result)
                    .and(specs.get(i));

        }
        return result;
    }
}
