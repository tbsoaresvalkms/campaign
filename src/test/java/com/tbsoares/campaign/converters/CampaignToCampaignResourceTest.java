package com.tbsoares.campaign.converters;

import com.tbsoares.campaign.models.Campaign;
import com.tbsoares.campaign.resources.CampaignResource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CampaignToCampaignResourceTest {
    private CampaignToCampaignResource campaignResourceToCampaign;

    @Before
    public void init() {
        campaignResourceToCampaign = new CampaignToCampaignResource();
    }

    @Test
    public void shouldConverter() {
        Campaign campaign = Campaign.builder()
                .id(1L)
                .name("name")
                .team("team")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .createdAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .build();

        CampaignResource campaignResource = campaignResourceToCampaign.convert(campaign);

        Assert.assertEquals(campaign.getId(), campaignResource.getId());
        Assert.assertEquals(campaign.getName(), campaignResource.getName());
        Assert.assertEquals(campaign.getTeam(), campaignResource.getTeam());
        Assert.assertEquals(campaign.getStartDate(), campaignResource.getStartDate());
        Assert.assertEquals(campaign.getEndDate(), campaignResource.getEndDate());
        Assert.assertEquals(campaign.getUpdateAt(), campaignResource.getUpdateAt());
        Assert.assertEquals(campaign.getCreatedAt(), campaignResource.getCreatedAt());
    }
}
