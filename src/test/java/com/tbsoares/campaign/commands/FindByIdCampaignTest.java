package com.tbsoares.campaign.commands;

import com.tbsoares.campaign.models.Campaign;
import com.tbsoares.campaign.repositories.CampaignRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


public class FindByIdCampaignTest {
    @Mock
    private CampaignRepository campaignRepository;
    @InjectMocks
    private FindByIdCampaign findByIdCampaign;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldGetCampaignById() {
        Long id = 1L;
        Campaign campaignOne = Campaign.builder()
                .id(1L)
                .name("campaign")
                .build();

        Mockito.when(campaignRepository.findOne(id))
                .thenReturn(campaignOne);

        Campaign campaign = findByIdCampaign.execute(id);

        Assert.assertEquals(campaignOne.getId(), campaign.getId());
        Assert.assertEquals(campaignOne.getName(), campaign.getName());
    }
}
