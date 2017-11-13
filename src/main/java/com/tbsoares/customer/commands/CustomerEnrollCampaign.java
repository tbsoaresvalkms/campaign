package com.tbsoares.customer.commands;

import com.tbsoares.campaign.resources.CampaignResource;
import com.tbsoares.customer.clients.CampaignClient;
import com.tbsoares.customer.resources.CustomerResource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerEnrollCampaign {
    private final CampaignClient campaignClient;
    private final FindAvailableCampaignByCustomer findAvailableCampaignByCustomer;

    private CustomerEnrollCampaign(CampaignClient campaignClient, FindAvailableCampaignByCustomer findAvailableCampaignByCustomer) {
        this.campaignClient = campaignClient;
        this.findAvailableCampaignByCustomer = findAvailableCampaignByCustomer;
    }

    public void execute(CustomerResource customerResource) {
        List<CampaignResource> campaignResources = findAvailableCampaignByCustomer.execute(customerResource);
        campaignResources.forEach(campaign -> campaignClient.enroll(customerResource, campaign));

    }
}
