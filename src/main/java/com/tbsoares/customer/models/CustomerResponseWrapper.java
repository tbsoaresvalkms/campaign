package com.tbsoares.customer.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tbsoares.campaign.resources.CampaignResource;
import com.tbsoares.customer.resources.CustomerResource;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CustomerResponseWrapper {
    private String message;
    private CustomerResource customer;
    private List<CampaignResource> availableCampaigns;
}
