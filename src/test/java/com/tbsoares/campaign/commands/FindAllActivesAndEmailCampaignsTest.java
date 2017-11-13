package com.tbsoares.campaign.commands;

import com.tbsoares.campaign.converters.CampaignToCampaignResource;
import com.tbsoares.campaign.models.Campaign;
import com.tbsoares.campaign.repositories.CampaignRepository;
import com.tbsoares.campaign.resources.CampaignResource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;


public class FindAllActivesAndEmailCampaignsTest {
    @Mock
    private CampaignRepository campaignRepository;
    @Mock
    private Converter converterMock;
    @InjectMocks
    private FindAllActivesAndEmailCampaigns findAllActivesAndEmailCampaigns;
    private CampaignToCampaignResource converter;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        converter = new CampaignToCampaignResource();
    }

    @Test
    public void shouldFindAllCampaignsByEmail() {
        String email = "email@email.com";
        Campaign campaignOne = Campaign.builder()
                .id(1L)
                .build();

        Campaign campaignTwo = Campaign.builder()
                .id(2L)
                .build();

        CampaignResource campaignResourceOne = convert(campaignOne);
        CampaignResource campaignResourceTwo = convert(campaignTwo);

        List<Campaign> campaigns = Arrays.asList(campaignOne, campaignTwo);

        Mockito.when(converterMock.convert(campaignOne, CampaignResource.class))
                .thenReturn(campaignResourceOne);

        Mockito.when(converterMock.convert(campaignTwo, CampaignResource.class))
                .thenReturn(campaignResourceTwo);

        Mockito.when(campaignRepository.findAllByAssociatesEmailAndEndDateGreaterThanEqual(email, LocalDate.now()))
                .thenReturn(campaigns);

        List<CampaignResource> campaignResources = findAllActivesAndEmailCampaigns.execute(email);

        Assert.assertEquals(2, campaignResources.size());
        Assert.assertEquals(campaignResourceOne.getId(), campaignResources.get(0).getId());
        Assert.assertEquals(campaignResourceTwo.getId(), campaignResources.get(1).getId());
    }

    private CampaignResource convert(Campaign campaign) {
        return converter.convert(campaign);
    }
}
