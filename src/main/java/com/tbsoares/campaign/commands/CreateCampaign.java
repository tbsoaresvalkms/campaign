package com.tbsoares.campaign.commands;

import com.tbsoares.campaign.models.Campaign;
import com.tbsoares.campaign.repositories.CampaignRepository;
import com.tbsoares.campaign.resources.CampaignResource;
import com.tbsoares.campaign.validators.CampaignValidator;
import org.springframework.stereotype.Component;

@Component
public class CreateCampaign {
    private final CampaignValidator campaignValidator;
    private final CampaignRepository campaignRepository;
    private final Converter converter;

    public CreateCampaign(CampaignValidator campaignValidator, CampaignRepository campaignRepository, Converter converter) {
        this.campaignValidator = campaignValidator;
        this.campaignRepository = campaignRepository;
        this.converter = converter;
    }

    public CampaignResource execute(CampaignResource campaignResource) {
        return toResource(campaignRepository.save(toEntity(valid(campaignResource))));
    }

    private CampaignResource toResource(Campaign campaign) {
        return converter.convert(campaign, CampaignResource.class);
    }

    private CampaignResource valid(CampaignResource campaignResource) {
        return campaignValidator.validate(campaignResource);
    }

    private Campaign toEntity(CampaignResource campaignResource) {
        return converter.convert(campaignResource, Campaign.class);
    }
}
