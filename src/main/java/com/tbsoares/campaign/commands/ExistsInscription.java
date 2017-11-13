package com.tbsoares.campaign.commands;

import com.tbsoares.campaign.repositories.AssociateRepository;
import org.springframework.stereotype.Component;

@Component
public class ExistsInscription {
    private final AssociateRepository associateRepository;

    public ExistsInscription(AssociateRepository associateRepository) {
        this.associateRepository = associateRepository;
    }


    public Boolean execute(Long campaignId, String email) {
        return associateRepository.existsByEmailAndCampaignsId(email, campaignId);
    }
}
