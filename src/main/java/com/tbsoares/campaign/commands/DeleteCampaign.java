package com.tbsoares.campaign.commands;

import com.tbsoares.campaign.exceptions.CampaignNotFoundException;
import com.tbsoares.campaign.models.Campaign;
import com.tbsoares.campaign.repositories.CampaignRepository;
import org.springframework.stereotype.Component;

import static com.tbsoares.campaign.utils.Optional.optional;

@Component
public class DeleteCampaign {
    private final CampaignRepository campaignRepository;

    public DeleteCampaign(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    public void execute(Long id) {
        optional(campaignRepository.findOne(id))
                .map(this::deleteCampaign)
                .orElseThrow(CampaignNotFoundException::new);
    }

    private Campaign deleteCampaign(Campaign campaign) {
        campaign.clearAssociate();
        campaignRepository.save(campaign);
        campaignRepository.delete(campaign.getId());
        return campaign;
    }
}
