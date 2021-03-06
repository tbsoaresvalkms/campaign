package com.tbsoares.campaign.commands;

import com.tbsoares.campaign.models.Campaign;
import com.tbsoares.campaign.repositories.CampaignRepository;
import com.tbsoares.campaign.resources.CampaignResource;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FindAllActivesAndEmailCampaigns {
    private final CampaignRepository campaignRepository;
    private final Converter converter;

    public FindAllActivesAndEmailCampaigns(CampaignRepository campaignRepository, Converter converter) {
        this.campaignRepository = campaignRepository;
        this.converter = converter;
    }

    public List<CampaignResource> execute(String email) {
        return campaignRepository.findAllByAssociatesEmailAndEndDateGreaterThanEqual(email, LocalDate.now())
                .stream()
                .map(this::toResource)
                .collect(Collectors.toList());
    }

    private CampaignResource toResource(Campaign campaign) {
        return converter.convert(campaign, CampaignResource.class);
    }
}
