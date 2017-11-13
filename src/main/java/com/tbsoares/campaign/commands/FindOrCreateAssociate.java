package com.tbsoares.campaign.commands;

import com.tbsoares.campaign.models.Associate;
import com.tbsoares.campaign.repositories.AssociateRepository;
import org.springframework.stereotype.Component;

import static com.tbsoares.campaign.utils.Optional.optional;


@Component
public class FindOrCreateAssociate {
    private final AssociateRepository associateRepository;

    public FindOrCreateAssociate(AssociateRepository associateRepository) {
        this.associateRepository = associateRepository;
    }

    public Associate execute(String email) {
        return optional(associateRepository.findByEmail(email))
                .orElse(create(email));
    }

    private Associate create(String email) {
        return Associate.builder()
                .email(email)
                .build();
    }
}