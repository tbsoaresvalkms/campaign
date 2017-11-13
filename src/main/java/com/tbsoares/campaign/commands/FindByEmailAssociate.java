package com.tbsoares.campaign.commands;

import com.tbsoares.campaign.exceptions.AssociateNotFoundException;
import com.tbsoares.campaign.models.Associate;
import com.tbsoares.campaign.repositories.AssociateRepository;
import com.tbsoares.campaign.resources.AssociateResource;
import org.springframework.stereotype.Component;

import static com.tbsoares.campaign.utils.Optional.optional;


@Component
public class FindByEmailAssociate {
    private final AssociateRepository associateRepository;
    private final Converter converter;

    public FindByEmailAssociate(AssociateRepository associateRepository, Converter converter) {
        this.associateRepository = associateRepository;
        this.converter = converter;
    }

    public AssociateResource execute(String email) {
        return optional(associateRepository.findByEmail(email))
                .map(this::toResource)
                .orElseThrow(AssociateNotFoundException::new);
    }

    private AssociateResource toResource(Associate associate) {
        return converter.convert(associate, AssociateResource.class);
    }
}
