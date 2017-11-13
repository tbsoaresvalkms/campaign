package com.tbsoares.campaign.converters;

import com.tbsoares.campaign.models.Campaign;
import com.tbsoares.campaign.resources.CampaignResource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CampaignResourceToCampaign implements Converter<CampaignResource, Campaign> {
    @Override
    public Campaign convert(CampaignResource campaignResource) {
        Campaign campaign = Campaign.builder()
                .id(campaignResource.getId())
                .name(campaignResource.getName())
                .team(campaignResource.getTeam())
                .startDate(campaignResource.getStartDate())
                .endDate(campaignResource.getEndDate())
                .build();
        campaignResource.getAssociates().forEach(campaign::addAssociate);
        return campaign;
    }
}
