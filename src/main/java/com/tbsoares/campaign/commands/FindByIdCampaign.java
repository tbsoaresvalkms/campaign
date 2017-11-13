package com.tbsoares.campaign.commands;

import com.tbsoares.campaign.models.Campaign;
import com.tbsoares.campaign.repositories.CampaignRepository;
import org.springframework.stereotype.Component;

@Component
public class FindByIdCampaign {
    private final CampaignRepository campaignRepository;

    public FindByIdCampaign(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    public Campaign execute(Long id) {
        return campaignRepository.findOne(id);
    }
}
