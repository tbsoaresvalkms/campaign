package com.tbsoares.customer.commands;

import com.tbsoares.campaign.resources.CampaignResource;
import com.tbsoares.customer.clients.CampaignClient;
import com.tbsoares.customer.resources.CustomerResource;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

public class CustomerEnrollCampaignTest {
    @Mock
    private FindAvailableCampaignByCustomer findAvailableCampaignByCustomer;
    @Mock
    private CampaignClient campaignClient;
    @InjectMocks
    private CustomerEnrollCampaign customerEnrollCampaign;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldEnrollAllAvailableCampains() {
        CustomerResource customerResource = CustomerResource.builder()
                .id(1L)
                .team("Team 1")
                .build();

        CampaignResource campaignOne = CampaignResource.builder()
                .team("Team 1")
                .id(1L)
                .build();

        CampaignResource campaignTwo = CampaignResource.builder()
                .team("Team 1")
                .id(2L)
                .build();

        List<CampaignResource> campaigns = Arrays.asList(campaignOne, campaignTwo);

        Mockito.when(findAvailableCampaignByCustomer.execute(customerResource))
                .thenReturn(campaigns);

        customerEnrollCampaign.execute(customerResource);

        Mockito.verify(campaignClient).enroll(customerResource, campaignOne);
        Mockito.verify(campaignClient).enroll(customerResource, campaignTwo);
    }

}
