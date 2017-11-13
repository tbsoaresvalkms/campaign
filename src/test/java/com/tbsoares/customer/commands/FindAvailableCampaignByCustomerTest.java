package com.tbsoares.customer.commands;

import com.tbsoares.campaign.commands.Converter;
import com.tbsoares.campaign.converters.CampaignToCampaignResource;
import com.tbsoares.campaign.models.Campaign;
import com.tbsoares.campaign.resources.CampaignResource;
import com.tbsoares.customer.clients.CampaignClient;
import com.tbsoares.customer.resources.CustomerResource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class FindAvailableCampaignByCustomerTest {

    @Mock
    private CampaignClient campaignClient;
    @Mock
    private Converter converterMock;
    @InjectMocks
    private FindAvailableCampaignByCustomer findAvailableCampaignByCustomer;
    private CampaignToCampaignResource converter;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        converter = new CampaignToCampaignResource();
    }

    @Test
    public void shouldFindAllAvailableCampaign() {
        CustomerResource customerResource = CustomerResource.builder()
                .id(1L)
                .email("customer1@email.com")
                .team("TEAM")
                .build();

        Campaign campaignOne = Campaign.builder()
                .id(1L)
                .team("TEAM")
                .build();

        Campaign campaignTwo = Campaign.builder()
                .id(2L)
                .team("TEAM")
                .build();

        CampaignResource campaignResourceTwo = convert(campaignTwo);

        List<Campaign> campaignsSameTeam = new ArrayList<>();
        campaignsSameTeam.add(campaignOne);
        campaignsSameTeam.add(campaignTwo);

        List<Campaign> campaignsAlreadyRegistered = new ArrayList<>();
        campaignsAlreadyRegistered.add(campaignOne);

        Mockito.when(converterMock.convert(campaignTwo, CampaignResource.class))
                .thenReturn(campaignResourceTwo);

        Mockito.when(campaignClient.getCampaignByTeam(customerResource.getTeam()))
                .thenReturn(campaignsSameTeam);

        Mockito.when(campaignClient.getCampaignByEmail(customerResource.getEmail()))
                .thenReturn(campaignsAlreadyRegistered);

        List<CampaignResource> campaignResources = findAvailableCampaignByCustomer.execute(customerResource);

        Assert.assertEquals(1, campaignResources.size());
        Assert.assertEquals(campaignResourceTwo.getId(), campaignResources.get(0).getId());
    }

    private CampaignResource convert(Campaign campaign) {
        return converter.convert(campaign);
    }
}
