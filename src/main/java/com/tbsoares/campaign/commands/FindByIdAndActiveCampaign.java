package com.tbsoares.campaign.commands;

import com.tbsoares.campaign.exceptions.CampaignNotFoundException;
import com.tbsoares.campaign.models.Campaign;
import com.tbsoares.campaign.repositories.CampaignRepository;
import com.tbsoares.campaign.resources.CampaignResource;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static com.tbsoares.campaign.utils.Optional.optional;


@Component
public class FindByIdAndActiveCampaign {
    private final CampaignRepository campaignRepository;
    private final Converter converter;

    public FindByIdAndActiveCampaign(CampaignRepository campaignRepository, Converter converter) {
        this.campaignRepository = campaignRepository;
        this.converter = converter;
    }

    public CampaignResource execute(Long id) {
        return optional(campaignRepository.findByIdAndEndDateGreaterThanEqual(id, LocalDate.now()))
                .map(this::toResource)
                .orElseThrow(CampaignNotFoundException::new);
    }

    private CampaignResource toResource(Campaign campaign) {
        return converter.convert(campaign, CampaignResource.class);
    }
}
