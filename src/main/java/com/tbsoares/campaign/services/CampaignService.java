package com.tbsoares.campaign.services;

import com.tbsoares.campaign.commands.*;
import com.tbsoares.campaign.resources.CampaignResource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CampaignService {

    private final FindAllActivesCampaigns findAllActivesCampaigns;
    private final FindAllActivesAndTeamCampaigns findAllActivesAndTeamCampaigns;
    private final FindByIdAndActiveCampaign findByIdAndActiveCampaign;
    private final CreateCampaign createCampaign;
    private final UpdateCampaign updateCampaign;
    private final DeleteCampaign deleteCampaign;

    public CampaignService(FindAllActivesCampaigns findAllActivesCampaigns, FindAllActivesAndTeamCampaigns findAllActivesAndTeamCampaigns, FindByIdAndActiveCampaign findByIdAndActiveCampaign, CreateCampaign createCampaign, UpdateCampaign updateCampaign, DeleteCampaign deleteCampaign) {
        this.findAllActivesCampaigns = findAllActivesCampaigns;
        this.findAllActivesAndTeamCampaigns = findAllActivesAndTeamCampaigns;
        this.findByIdAndActiveCampaign = findByIdAndActiveCampaign;
        this.createCampaign = createCampaign;
        this.updateCampaign = updateCampaign;
        this.deleteCampaign = deleteCampaign;
    }


    public List<CampaignResource> allCampaignActive() {
        return findAllActivesCampaigns.execute();
    }

    public List<CampaignResource> allCampaignActiveByTeam(String team) {
        return findAllActivesAndTeamCampaigns.execute(team);
    }

    public CampaignResource getCampaign(Long id) {
        return findByIdAndActiveCampaign.execute(id);
    }

    public CampaignResource create(CampaignResource campaignResource) {
        return createCampaign.execute(campaignResource);
    }

    public CampaignResource update(CampaignResource campaignResource) {
        return updateCampaign.execute(campaignResource);
    }

    public void delete(Long id) {
        deleteCampaign.execute(id);
    }

}
