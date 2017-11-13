package com.tbsoares.campaign.commands;

import com.tbsoares.campaign.exceptions.CampaignNotFoundException;
import com.tbsoares.campaign.models.Campaign;
import com.tbsoares.campaign.repositories.CampaignRepository;
import com.tbsoares.campaign.resources.CampaignResource;
import com.tbsoares.campaign.validators.CampaignValidator;
import org.springframework.stereotype.Component;

import static com.tbsoares.campaign.utils.Optional.optional;

@Component
public class UpdateCampaign {
    private final CampaignValidator campaignValidator;
    private final CampaignRepository campaignRepository;
    private final Converter converter;

    public UpdateCampaign(CampaignValidator campaignValidator, CampaignRepository campaignRepository, Converter converter) {
        this.campaignValidator = campaignValidator;
        this.campaignRepository = campaignRepository;
        this.converter = converter;
    }

    public CampaignResource execute(CampaignResource campaignResource) {
        Campaign campaign = optional(campaignRepository.findOne(campaignResource.getId()))
                .orElseThrow(CampaignNotFoundException::new);

        return toResource(campaignRepository.save(campaign.update(toEntity(valid(campaignResource)))));
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
