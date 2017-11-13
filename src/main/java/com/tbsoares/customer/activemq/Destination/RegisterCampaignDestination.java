package com.tbsoares.customer.activemq.Destination;


import org.springframework.stereotype.Component;

@Component
public class RegisterCampaignDestination implements Destination {

    private final String ROUTE = "register-campaignId";

    @Override
    public String getRoute() {
        return ROUTE;
    }
}
