package com.tbsoares.campaign.converters;

import com.tbsoares.campaign.models.Campaign;
import com.tbsoares.campaign.resources.CampaignResource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CampaignToCampaignResource implements Converter<Campaign, CampaignResource> {
    @Override
    public CampaignResource convert(Campaign campaign) {
        return CampaignResource.builder()
                .id(campaign.getId())
                .name(campaign.getName())
                .team(campaign.getTeam())
                .startDate(campaign.getStartDate())
                .endDate(campaign.getEndDate())
                .createdAt(campaign.getCreatedAt())
                .updateAt(campaign.getUpdateAt())
                .build();
    }
}
