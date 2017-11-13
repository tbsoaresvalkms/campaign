package com.tbsoares.campaign.commands;

import com.tbsoares.campaign.exceptions.CampaignNotFoundException;
import com.tbsoares.campaign.models.Associate;
import com.tbsoares.campaign.models.Campaign;
import com.tbsoares.campaign.repositories.CampaignRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;


public class DeleteCampaignTest {
    @Mock
    private CampaignRepository campaignRepository;
    @InjectMocks
    private DeleteCampaign deleteCampaign;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = CampaignNotFoundException.class)
    public void throwExceptionWhenCampaignNotFound() {
        deleteCampaign.execute(1L);
    }

    @Test
    public void shouldDeleteCampaign() {
        Campaign campaign = Campaign.builder()
                .id(1L)
                .name("name")
                .team("team")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .build();

        Mockito.when(campaignRepository.findOne(1L))
                .thenReturn(campaign);

        deleteCampaign.execute(1L);

        Mockito.verify(campaignRepository).findOne(1L);
        Mockito.verify(campaignRepository).delete(1L);
    }

    @Test
    public void shouldClearAssociate() {
        Campaign campaign = Campaign.builder()
                .id(1L)
                .name("name")
                .team("team")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .build();

        campaign.addAssociate(new Associate());

        Mockito.when(campaignRepository.findOne(1L))
                .thenReturn(campaign);

        deleteCampaign.execute(1L);

        Assert.assertEquals(0, campaign.getAssociates().size());
    }
}
