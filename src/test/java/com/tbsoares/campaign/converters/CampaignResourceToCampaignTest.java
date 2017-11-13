package com.tbsoares.campaign.converters;

import com.tbsoares.campaign.models.Campaign;
import com.tbsoares.campaign.resources.CampaignResource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class CampaignResourceToCampaignTest {
    private CampaignResourceToCampaign campaignResourceToCampaign;

    @Before
    public void init() {
        campaignResourceToCampaign = new CampaignResourceToCampaign();
    }

    @Test
    public void shouldConverter() {
        CampaignResource campaignResource = CampaignResource.builder()
                .id(1L)
                .name("name")
                .team("team")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .build();

        Campaign campaign = campaignResourceToCampaign.convert(campaignResource);

        Assert.assertEquals(campaignResource.getId(), campaign.getId());
        Assert.assertEquals(campaignResource.getName(), campaign.getName());
        Assert.assertEquals(campaignResource.getTeam(), campaign.getTeam());
        Assert.assertEquals(campaignResource.getStartDate(), campaign.getStartDate());
        Assert.assertEquals(campaignResource.getEndDate(), campaign.getEndDate());
    }
}
