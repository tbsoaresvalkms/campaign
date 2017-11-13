package com.tbsoares.campaign.services;

import com.tbsoares.campaign.commands.*;
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

public class CampaignServiceTest {
    @Mock
    private FindAllActivesCampaigns findAllActivesCampaigns;
    @Mock
    private FindAllActivesAndTeamCampaigns findAllActivesAndTeamCampaigns;
    @Mock
    private FindByIdAndActiveCampaign findByIdAndActiveCampaign;
    @Mock
    private CreateCampaign createCampaign;
    @Mock
    private UpdateCampaign updateCampaign;
    @Mock
    private DeleteCampaign deleteCampaign;
    @InjectMocks
    private CampaignService campaignService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldGetAllCampaignActive() {
        List<CampaignResource> campaignResources = Collections.singletonList(CampaignResource.builder().build());
        Mockito.when(findAllActivesCampaigns.execute())
                .thenReturn(campaignResources);

        List<CampaignResource> campaigns = campaignService.allCampaignActive();

        Assert.assertEquals(campaignResources, campaigns);
        Mockito.verify(findAllActivesCampaigns).execute();
        Mockito.verifyNoMoreInteractions(findAllActivesCampaigns);
    }

    @Test
    public void shouldGetAllCampaignActiveByTeam() {
        String team = "TEAM";
        List<CampaignResource> campaignResources = Collections.singletonList(CampaignResource.builder().build());
        Mockito.when(findAllActivesAndTeamCampaigns.execute(team))
                .thenReturn(campaignResources);

        List<CampaignResource> campaigns = campaignService.allCampaignActiveByTeam(team);

        Assert.assertEquals(campaignResources, campaigns);
        Mockito.verify(findAllActivesAndTeamCampaigns).execute(team);
        Mockito.verifyNoMoreInteractions(findAllActivesAndTeamCampaigns);
    }

    @Test
    public void shouldGetCampaign() {
        Long id = 1L;
        CampaignResource campaignResources = CampaignResource.builder().build();
        Mockito.when(findByIdAndActiveCampaign.execute(id))
                .thenReturn(campaignResources);

        CampaignResource campaign = campaignService.getCampaign(id);

        Assert.assertEquals(campaignResources, campaign);
        Mockito.verify(findByIdAndActiveCampaign).execute(id);
        Mockito.verifyNoMoreInteractions(findByIdAndActiveCampaign);
    }

    @Test
    public void shouldCreateCampaign() {
        CampaignResource campaignToCreate = CampaignResource.builder()
                .name("campaign")
                .team("team")
                .build();

        CampaignResource campaignCreated = CampaignResource.builder()
                .id(1L)
                .name("campaign")
                .team("TEAM")
                .build();

        Mockito.when(createCampaign.execute(campaignToCreate))
                .thenReturn(campaignCreated);

        CampaignResource campaign = campaignService.create(campaignToCreate);

        Assert.assertEquals(campaignCreated, campaign);
        Mockito.verify(createCampaign).execute(campaignToCreate);
        Mockito.verifyNoMoreInteractions(createCampaign);
    }

    @Test
    public void shouldUpdateCampaign() {
        CampaignResource campaignToUpdate = CampaignResource.builder()
                .id(1L)
                .name("campaign")
                .team("team")
                .build();

        CampaignResource campaignUpdated = CampaignResource.builder()
                .id(1L)
                .name("campaign")
                .team("TEAM")
                .build();

        Mockito.when(updateCampaign.execute(campaignToUpdate))
                .thenReturn(campaignUpdated);

        CampaignResource campaign = campaignService.update(campaignToUpdate);

        Assert.assertEquals(campaignUpdated, campaign);
        Mockito.verify(updateCampaign).execute(campaignToUpdate);
        Mockito.verifyNoMoreInteractions(updateCampaign);
    }

    @Test
    public void shouldDeleteCampaign() {
        Long id = 1L;

        campaignService.delete(id);

        Mockito.verify(deleteCampaign).execute(id);
        Mockito.verifyNoMoreInteractions(deleteCampaign);
    }
}
