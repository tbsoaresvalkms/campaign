package com.tbsoares.customer.clients;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tbsoares.campaign.models.Campaign;
import com.tbsoares.campaign.models.Inscription;
import com.tbsoares.campaign.resources.CampaignResource;
import com.tbsoares.customer.exceptions.CampaignClientException;
import com.tbsoares.customer.resources.CustomerResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CampaignClient {

    private RestTemplate restTemplate;
    private ObjectMapper objectMapper;
    private String campaignUrl;
    private String associateUrl;


    public CampaignClient(RestTemplate restTemplate,
                          @Value("${service.camapign.url}") String campaignUrl,
                          @Value("${service.associate.url}") String associateUrl,
                          ObjectMapper objectMapper) {

        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.campaignUrl = campaignUrl;
        this.associateUrl = associateUrl;
    }

    public List<Campaign> getCampaignByTeam(String team) {
        return getCampaigns(campaignUrl + "/team/" + team);
    }

    public List<Campaign> getCampaignByEmail(String email) {
        return getCampaigns(associateUrl + "/campaign?email=" + email);
    }

    public void enroll(CustomerResource customerResource, CampaignResource campaignResource) {
        try {
            HttpEntity request = builderRequest(customerResource, campaignResource);
            restTemplate.postForEntity(associateUrl, request, String.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CampaignClientException();
        }
    }

    private HttpEntity builderRequest(CustomerResource customer, CampaignResource campaign) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Inscription inscription = Inscription.builder()
                .email(customer.getEmail())
                .campaignId(campaign.getId())
                .build();

        String body = objectMapper.writeValueAsString(inscription);
        return new HttpEntity(body, headers);
    }

    private List<Campaign> getCampaigns(String url) {
        try {
            Campaign[] campaigns = restTemplate.getForObject(url, Campaign[].class);
            return Arrays.stream(campaigns).collect(Collectors.toList());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CampaignClientException();
        }
    }

}
