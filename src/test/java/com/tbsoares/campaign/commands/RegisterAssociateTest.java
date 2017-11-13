package com.tbsoares.campaign.commands;

import com.tbsoares.campaign.exceptions.AlreadyRegisteredException;
import com.tbsoares.campaign.exceptions.CampaignNotFoundException;
import com.tbsoares.campaign.models.Associate;
import com.tbsoares.campaign.models.Inscription;
import com.tbsoares.campaign.resources.CampaignResource;
import com.tbsoares.campaign.validators.InscriptionValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class RegisterAssociateTest {

    @Mock
    private FindByIdAndActiveCampaign findByIdAndActiveCampaign;
    @Mock
    private UpdateCampaign updateCampaign;
    @Mock
    private FindOrCreateAssociate findOrCreateAssociate;
    @Mock
    private InscriptionValidator inscriptionValidator;
    @InjectMocks
    private RegisterAssociate registerAssociate;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = AlreadyRegisteredException.class)
    public void throwExceptionWhenAlreadyRegistered() {
        Inscription inscription = Inscription.builder()
                .campaignId(1L)
                .email("email@email.com")
                .build();

        Mockito.when(inscriptionValidator.validate(inscription))
                .thenThrow(AlreadyRegisteredException.class);

        registerAssociate.execute(inscription);
    }

    @Test(expected = CampaignNotFoundException.class)
    public void throwExceptionWhenCampaignNotFound() {
        Inscription inscription = Inscription.builder()
                .campaignId(1L)
                .email("email@email.com")
                .build();

        Mockito.when(inscriptionValidator.validate(inscription))
                .thenReturn(inscription);

        Mockito.when(findByIdAndActiveCampaign.execute(inscription.getCampaignId()))
                .thenThrow(CampaignNotFoundException.class);

        registerAssociate.execute(inscription);
    }

    @Test
    public void shouldRegisterAssociate() {
        String email = "email@email.com";

        Inscription inscription = Inscription.builder()
                .campaignId(1L)
                .email("email@email.com")
                .build();

        CampaignResource campaign = CampaignResource.builder()
                .id(1L)
                .name("campaign")
                .build();
        Associate associate = Associate.builder()
                .id(1L)
                .email("email@email.com")
                .build();

        Mockito.when(inscriptionValidator.validate(inscription))
                .thenReturn(inscription);

        Mockito.when(findByIdAndActiveCampaign.execute(inscription.getCampaignId()))
                .thenReturn(campaign);

        Mockito.when(findOrCreateAssociate.execute(email))
                .thenReturn(associate);

        registerAssociate.execute(inscription);

        Assert.assertEquals(1, campaign.getAssociates().size());
        Assert.assertEquals(associate, campaign.getAssociates().get(0));
        Mockito.verify(updateCampaign).execute(campaign);
    }
}
