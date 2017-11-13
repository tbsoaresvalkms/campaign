package com.tbsoares.campaign.commands;

import com.tbsoares.campaign.converters.CampaignToCampaignResource;
import com.tbsoares.campaign.exceptions.CampaignNotFoundException;
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


public class FindByIdAndActiveCampaignTest {
    @Mock
    private CampaignRepository campaignRepository;
    @Mock
    private Converter converterMock;
    @InjectMocks
    private FindByIdAndActiveCampaign findByIdAndActiveCampaign;
    private CampaignToCampaignResource converter;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        converter = new CampaignToCampaignResource();
    }

    @Test
    public void shouldGetCampaignById() {
        Long id = 1L;
        Campaign campaignOne = Campaign.builder()
                .id(1L)
                .build();

        CampaignResource campaignResource = convert(campaignOne);

        Mockito.when(converterMock.convert(campaignOne, CampaignResource.class))
                .thenReturn(campaignResource);

        Mockito.when(campaignRepository.findByIdAndEndDateGreaterThanEqual(id, LocalDate.now()))
                .thenReturn(campaignOne);

        CampaignResource actual = findByIdAndActiveCampaign.execute(id);

        Assert.assertEquals(campaignResource.getId(), actual.getId());
    }

    @Test(expected = CampaignNotFoundException.class)
    public void throwExceptionWhenCampaignNotFound() {
        findByIdAndActiveCampaign.execute(1L);
    }

    private CampaignResource convert(Campaign campaign) {
        return converter.convert(campaign);
    }
}
