package com.tbsoares.campaign.commands;

import com.tbsoares.campaign.converters.CampaignResourceToCampaign;
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

public class CreateCampaignTest {
    @Mock
    private CampaignValidator campaignValidator;
    @Mock
    private CampaignRepository campaignRepository;
    @Mock
    private Converter converterMock;
    @InjectMocks
    private CreateCampaign createCampaign;
    private CampaignResourceToCampaign converter;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        converter = new CampaignResourceToCampaign();
    }

    @Test
    public void shouldSaveCampaign() {
        CampaignResource toCreate = CampaignResource.builder()
                .id(-1L)
                .name("name")
                .team("team")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .build();

        CampaignResource created = CampaignResource.builder()
                .id(1L)
                .name("name")
                .team("TEAM")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .build();


        Campaign campaignToCreate = convert(toCreate);
        Campaign campaignCreated = convert(created);

        Mockito.when(campaignValidator.validate(toCreate))
                .thenReturn(toCreate);

        Mockito.when(converterMock.convert(toCreate, Campaign.class))
                .thenReturn(campaignToCreate);

        Mockito.when(campaignRepository.save(campaignToCreate))
                .thenReturn(campaignCreated);

        Mockito.when(converterMock.convert(campaignCreated, CampaignResource.class))
                .thenReturn(created);

        CampaignResource campaignResource = createCampaign.execute(toCreate);

        Assert.assertEquals(created.getId(), campaignResource.getId());
        Assert.assertEquals(created.getName(), campaignResource.getName());
        Assert.assertEquals(created.getTeam(), campaignResource.getTeam());
        Assert.assertEquals(created.getStartDate(), campaignResource.getStartDate());
        Assert.assertEquals(created.getEndDate(), campaignResource.getEndDate());
    }


    @Test(expected = StartDateAfterEndDateException.class)
    public void noShouldSaveWhenThrowException() {
        CampaignResource toCreate = CampaignResource.builder()
                .id(-1L)
                .name("name")
                .team("team")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .build();

        Mockito.when(campaignValidator.validate(toCreate))
                .thenThrow(StartDateAfterEndDateException.class);

        createCampaign.execute(toCreate);
        Mockito.verifyNoMoreInteractions(campaignRepository);
    }

    private Campaign convert(CampaignResource campaignResource) {
        Campaign convert = converter.convert(campaignResource);
        return convert;
    }

}
