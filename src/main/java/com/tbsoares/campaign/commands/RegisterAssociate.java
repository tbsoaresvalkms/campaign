package com.tbsoares.campaign.commands;

import com.tbsoares.campaign.models.Associate;
import com.tbsoares.campaign.models.Inscription;
import com.tbsoares.campaign.resources.CampaignResource;
import com.tbsoares.campaign.validators.InscriptionValidator;
import org.springframework.stereotype.Component;

@Component
public class RegisterAssociate {
    private final FindByIdAndActiveCampaign findByIdAndActiveCampaign;
    private final UpdateCampaign updateCampaign;
    private final FindOrCreateAssociate findOrCreateAssociate;
    private final InscriptionValidator inscriptionValidator;

    public RegisterAssociate(FindByIdAndActiveCampaign findByIdAndActiveCampaign, UpdateCampaign updateCampaign, FindOrCreateAssociate findOrCreateAssociate, InscriptionValidator inscriptionValidator) {
        this.findByIdAndActiveCampaign = findByIdAndActiveCampaign;
        this.updateCampaign = updateCampaign;
        this.findOrCreateAssociate = findOrCreateAssociate;
        this.inscriptionValidator = inscriptionValidator;
    }

    public void execute(Inscription inscription) {
        register(valid(inscription));
    }

    private Inscription valid(Inscription inscription) {
        return inscriptionValidator.validate(inscription);
    }

    private void register(Inscription inscription) {
        Long campaignId = inscription.getCampaignId();
        String email = inscription.getEmail();

        CampaignResource campaign = findByIdAndActiveCampaign.execute(campaignId);
        Associate associate = findOrCreateAssociate.execute(email);
        campaign.addAssociate(associate);
        updateCampaign.execute(campaign);
    }
}
