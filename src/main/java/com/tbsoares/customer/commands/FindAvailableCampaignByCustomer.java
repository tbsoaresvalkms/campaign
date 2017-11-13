package com.tbsoares.customer.commands;

import com.tbsoares.campaign.commands.Converter;
import com.tbsoares.campaign.models.Campaign;
import com.tbsoares.campaign.resources.CampaignResource;
import com.tbsoares.customer.clients.CampaignClient;
import com.tbsoares.customer.resources.CustomerResource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FindAvailableCampaignByCustomer {

    private final CampaignClient campaignClient;
    private final Converter converter;

    public FindAvailableCampaignByCustomer(CampaignClient campaignClient, Converter converter) {
        this.campaignClient = campaignClient;
        this.converter = converter;
    }

    public List<CampaignResource> execute(CustomerResource customerResource) {
        List<Campaign> campaignByTeam = campaignClient.getCampaignByTeam(customerResource.getTeam());
        List<Campaign> campaignByEmail = campaignClient.getCampaignByEmail(customerResource.getEmail());
        campaignByTeam.removeAll(campaignByEmail);

        return campaignByTeam
                .stream()
                .map(this::toResource)
                .collect(Collectors.toList());
    }

    private CampaignResource toResource(Campaign campaign) {
        return converter.convert(campaign, CampaignResource.class);
    }
}
