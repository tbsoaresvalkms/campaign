package com.tbsoares.campaign.services;

import com.tbsoares.campaign.commands.FindAllActivesAndEmailCampaigns;
import com.tbsoares.campaign.commands.FindByEmailAssociate;
import com.tbsoares.campaign.commands.RegisterAssociate;
import com.tbsoares.campaign.models.Inscription;
import com.tbsoares.campaign.resources.CampaignResource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AssociateService {
    private final RegisterAssociate registerAssociate;
    private final FindAllActivesAndEmailCampaigns findAllActivesAndEmailCampaigns;
    private final FindByEmailAssociate findByEmailAssociate;

    public AssociateService(RegisterAssociate registerAssociate, FindAllActivesAndEmailCampaigns findAllActivesAndEmailCampaigns, FindByEmailAssociate findByEmailAssociate) {
        this.registerAssociate = registerAssociate;
        this.findAllActivesAndEmailCampaigns = findAllActivesAndEmailCampaigns;
        this.findByEmailAssociate = findByEmailAssociate;
    }


    public void inscriptionCampaign(Inscription inscription) {
        registerAssociate.execute(inscription);
    }

    public List<CampaignResource> getCampaignsByEmail(String email) {
        //verifyIfEmailExist(email);
        return findAllActivesAndEmailCampaigns.execute(email);
    }

    private void verifyIfEmailExist(String email) {
        findByEmailAssociate.execute(email);
    }
}
