package com.tbsoares.campaign.services;

import com.tbsoares.campaign.commands.*;
import com.tbsoares.campaign.exceptions.AssociateNotFoundException;
import com.tbsoares.campaign.models.Inscription;
import com.tbsoares.campaign.resources.CampaignResource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

public class AssociateServiceTest {
    @Mock
    private RegisterAssociate registerAssociate;
    @Mock
    private FindAllActivesAndEmailCampaigns findAllActivesAndEmailCampaigns;
    @Mock
    private FindByEmailAssociate findByEmailAssociate;

    @InjectMocks
    private AssociateService associateService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldRegisterAssociate() {
        Inscription inscription = Inscription.builder()
                .campaignId(1L)
                .email("email@email.com")
                .build();

        associateService.inscriptionCampaign(inscription);

        Mockito.verify(registerAssociate).execute(inscription);
        Mockito.verifyNoMoreInteractions(registerAssociate);
    }

    @Test
    public void shouldGetAllActivesAndEmailCampaigns() {
        String email = "email@email.com";
        List<CampaignResource> campaignResources = Collections.singletonList(CampaignResource.builder().build());
        Mockito.when(findAllActivesAndEmailCampaigns.execute(email))
                .thenReturn(campaignResources);

        List<CampaignResource> campaigns = associateService.getCampaignsByEmail(email);

        Assert.assertEquals(campaignResources, campaigns);
        Mockito.verify(findAllActivesAndEmailCampaigns).execute(email);
        Mockito.verifyNoMoreInteractions(findAllActivesAndEmailCampaigns);
    }
}

