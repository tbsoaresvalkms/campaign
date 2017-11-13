package com.tbsoares.campaign.commands;

import com.tbsoares.campaign.converters.CampaignResourceToCampaign;
import com.tbsoares.campaign.exceptions.CampaignNotFoundException;
import com.tbsoares.campaign.exceptions.StartDateAfterEndDateException;
import com.tbsoares.campaign.models.Campaign;
import com.tbsoares.campaign.repositories.CampaignRepository;
import com.tbsoares.campaign.resources.CampaignResource;
import com.tbsoares.campaign.validators.CampaignValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

public class UpdateCampaignTest {
    @Mock
    private CampaignValidator campaignValidator;
    @Mock
    private CampaignRepository campaignRepository;
    @Mock
    private Converter converterMock;
    @InjectMocks
    private UpdateCampaign updateCampaign;
    private CampaignResourceToCampaign converter;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        converter = new CampaignResourceToCampaign();
    }

    @Test(expected = CampaignNotFoundException.class)
    public void throwExceptionWhenCampaignNotFound() {
        CampaignResource toUpdate = CampaignResource.builder()
                .id(1L)
                .name("new name")
                .team("team")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .build();

        updateCampaign.execute(toUpdate);
    }

    @Test
    public void shouldUpdateCampaign() {
        CampaignResource toUpdate = CampaignResource.builder()
                .id(1L)
                .name("new name")
                .team("team")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .build();

        Campaign campaignOld = Campaign.builder()
                .id(1L)
                .name("name")
                .team("TEAM")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .build();

        CampaignResource update = CampaignResource.builder()
                .id(1L)
                .name("new name")
                .team("TEAM")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .build();

        Campaign campaignToUpdate = convert(toUpdate);

        Mockito.when(campaignRepository.findOne(toUpdate.getId()))
                .thenReturn(campaignOld);

        Mockito.when(campaignValidator.validate(toUpdate))
                .thenReturn(toUpdate);

        Mockito.when(converterMock.convert(toUpdate, Campaign.class))
                .thenReturn(campaignToUpdate);

        Mockito.when(campaignRepository.save(campaignOld))
                .thenReturn(campaignToUpdate);

        Mockito.when(converterMock.convert(campaignToUpdate, CampaignResource.class))
                .thenReturn(update);

        CampaignResource campaignResource = updateCampaign.execute(toUpdate);

        Assert.assertEquals(toUpdate.getId(), campaignResource.getId());
        Assert.assertEquals(toUpdate.getName(), campaignResource.getName());
        Assert.assertEquals(toUpdate.getTeam().toUpperCase(), campaignResource.getTeam());
    }


    @Test(expected = StartDateAfterEndDateException.class)
    public void noShouldUpdateWhenThrowException() {
        CampaignResource toUpdate = CampaignResource.builder()
                .id(-1L)
                .name("name")
                .team("team")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .build();

        Campaign campaignOld = Campaign.builder()
                .id(1L)
                .name("name")
                .team("TEAM")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .build();

        Mockito.when(campaignRepository.findOne(toUpdate.getId()))
                .thenReturn(campaignOld);

        Mockito.when(campaignValidator.validate(toUpdate))
                .thenThrow(StartDateAfterEndDateException.class);

        updateCampaign.execute(toUpdate);
        Mockito.verifyNoMoreInteractions(campaignRepository);
    }

    private Campaign convert(CampaignResource campaignResource) {
        Campaign convert = converter.convert(campaignResource);
        return convert;
    }

}
