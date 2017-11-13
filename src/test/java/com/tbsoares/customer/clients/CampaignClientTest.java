package com.tbsoares.customer.clients;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tbsoares.campaign.models.Campaign;
import com.tbsoares.campaign.resources.CampaignResource;
import com.tbsoares.customer.resources.CustomerResource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;

public class CampaignClientTest {

    @Mock
    private RestTemplate restTemplate;
    @Mock
    private ObjectMapper objectMapper;
    @InjectMocks
    private CampaignClient campaignClient;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldGetCampaignsByTeam() {
        Campaign[] campaigns = new Campaign[2];
        campaigns[0] = Campaign.builder()
                .team("team")
                .build();
        campaigns[1] = Campaign.builder()
                .team("team")
                .build();

        Mockito.when(restTemplate.getForObject(anyString(), eq(Campaign[].class)))
                .thenReturn(campaigns);

        List<Campaign> campaignByTeam = campaignClient.getCampaignByTeam("team");

        Assert.assertEquals(2, campaignByTeam.size());
        Assert.assertEquals(campaigns[0].getTeam(), campaignByTeam.get(0).getTeam());
        Assert.assertEquals(campaigns[1].getTeam(), campaignByTeam.get(1).getTeam());
    }


    @Test
    public void shouldGetCampaignsByEmail() {
        Campaign[] campaigns = new Campaign[2];
        campaigns[0] = Campaign.builder()
                .name("campaign 1")
                .build();
        campaigns[1] = Campaign.builder()
                .name("campaign 2")
                .build();

        Mockito.when(restTemplate.getForObject(anyString(), eq(Campaign[].class)))
                .thenReturn(campaigns);

        List<Campaign> campaignByTeam = campaignClient.getCampaignByTeam("team");

        Assert.assertEquals(2, campaignByTeam.size());
        Assert.assertEquals(campaigns[0].getTeam(), campaignByTeam.get(0).getTeam());
        Assert.assertEquals(campaigns[1].getTeam(), campaignByTeam.get(1).getTeam());
    }


    @Test
    public void shouldEnrollCustomer() {
        CustomerResource customerResource = CustomerResource.builder()
                .id(-1L)
                .fullName("Full Name")
                .team("team")
                .build();

        CampaignResource campaignResource = CampaignResource.builder()
                .name("campaign 1")
                .build();

        campaignClient.enroll(customerResource, campaignResource);
        Mockito.verify(restTemplate).postForEntity(anyString(), anyObject(), eq(String.class));
    }
}
