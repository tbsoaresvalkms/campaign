package com.tbsoares.campaign.services;

import com.tbsoares.campaign.models.Campaign;
import com.tbsoares.campaign.repositories.CampaignRepository;
import com.tbsoares.campaign.resources.CampaignResource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

public class AdjustEndPeriodTest {

    @Mock
    private CampaignRepository campaignRepository;
    @InjectMocks
    private AdjustEndPeriod adjustEndPeriod;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void noShouldUpdateWhenNoConflict() {
        Long id = 1L;
        LocalDate endDate = LocalDate.now();
        CampaignResource campaignResource = CampaignResource.builder()
                .id(id)
                .endDate(endDate)
                .build();

        adjustEndPeriod.execute(campaignResource);

        Mockito.verify(campaignRepository).findByIdNotAndEndDate(id, endDate);
        Mockito.verifyNoMoreInteractions(campaignRepository);
    }

    @Test
    public void shouldUpdateOneCampaignWhenHasConflict() {
        Long campaignResourceId = 1L;
        LocalDate campaignResourceEndDate = LocalDate.now();

        CampaignResource campaignResource = CampaignResource.builder()
                .id(campaignResourceId)
                .endDate(campaignResourceEndDate)
                .build();

        Campaign campaign = builderCampaign(2L, LocalDate.now());

        Mockito.when(campaignRepository.findByIdNotAndEndDate(campaignResourceId, campaignResourceEndDate))
                .thenReturn(campaign);

        adjustEndPeriod.execute(campaignResource);
        LocalDate newEndDate = campaign.getEndDate();

        Mockito.verify(campaignRepository).findByIdNotAndEndDate(campaignResourceId, campaignResourceEndDate);
        Mockito.verify(campaignRepository).findByIdNotAndEndDate(campaignResourceId, newEndDate);
        Mockito.verify(campaignRepository).save(campaign);
        Mockito.verifyNoMoreInteractions(campaignRepository);
    }

    @Test
    public void shouldUpdateAllCampaignWhenHasManyConflict() {
        Long campaignResourceId = 1L;
        LocalDate campaignResourceEndDate = LocalDate.now();

        Campaign firstCampaign = builderCampaign(2L, LocalDate.now());
        Campaign secondCampaign = builderCampaign(3L, LocalDate.now().plusDays(1));

        CampaignResource campaignResource = CampaignResource.builder()
                .id(campaignResourceId)
                .endDate(campaignResourceEndDate)
                .build();

        Mockito.when(campaignRepository.findByIdNotAndEndDate(1L, firstCampaign.getEndDate()))
                .thenReturn(firstCampaign);

        Mockito.when(campaignRepository.findByIdNotAndEndDate(1L, secondCampaign.getEndDate()))
                .thenReturn(secondCampaign);

        adjustEndPeriod.execute(campaignResource);

        Assert.assertEquals(LocalDate.now(), campaignResource.getEndDate());
        Assert.assertEquals(LocalDate.now().plusDays(1), firstCampaign.getEndDate());
        Assert.assertEquals(LocalDate.now().plusDays(2), secondCampaign.getEndDate());


        Mockito.verify(campaignRepository).findByIdNotAndEndDate(1L, LocalDate.now());
        Mockito.verify(campaignRepository).findByIdNotAndEndDate(1L, LocalDate.now().plusDays(1));
        Mockito.verify(campaignRepository).findByIdNotAndEndDate(1L, LocalDate.now().plusDays(2));
        Mockito.verify(campaignRepository).save(firstCampaign);
        Mockito.verify(campaignRepository).save(secondCampaign);
        Mockito.verifyNoMoreInteractions(campaignRepository);
    }

    @Test
    public void noShouldUpdateWhenSameIdCauseIsUpdateCampaign() {
        Long campaignResourceId = 1L;
        LocalDate campaignResourceEndDate = LocalDate.now();

        CampaignResource campaignResource = CampaignResource.builder()
                .id(campaignResourceId)
                .endDate(campaignResourceEndDate)
                .build();

        adjustEndPeriod.execute(campaignResource);

        Mockito.verify(campaignRepository).findByIdNotAndEndDate(1L, campaignResourceEndDate);
        Mockito.verifyNoMoreInteractions(campaignRepository);
    }

    private Campaign builderCampaign(Long id, LocalDate localDate) {
        return Campaign.builder()
                .id(id)
                .endDate(localDate)
                .build();
    }

}
