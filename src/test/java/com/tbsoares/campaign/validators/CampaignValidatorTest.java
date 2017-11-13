package com.tbsoares.campaign.validators;

import com.tbsoares.campaign.exceptions.StartDateAfterEndDateException;
import com.tbsoares.campaign.resources.CampaignResource;
import com.tbsoares.campaign.services.AdjustEndPeriod;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

public class CampaignValidatorTest {
    @Mock
    private AdjustEndPeriod adjustEndPeriod;
    @InjectMocks
    private CampaignValidator campaignValidator;
    private CampaignResource campaignResource;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        campaignResource = CampaignResource.builder()
                .id(-1L)
                .name("name")
                .team("team")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .build();
    }

    @Test
    public void shouldBeSameObject() {
        CampaignResource actual = campaignValidator.validate(campaignResource);
        Assert.assertEquals(campaignResource, actual);
    }

    @Test
    public void shouldUpperCaseTeam() {
        campaignResource.setTeam("uppercase");
        CampaignResource actual = campaignValidator.validate(campaignResource);
        Assert.assertEquals("UPPERCASE", actual.getTeam());
    }

    @Test(expected = StartDateAfterEndDateException.class)
    public void throwExceptionWhenStarDateAfterEndDate() {
        campaignResource.setStartDate(LocalDate.now());
        campaignResource.setEndDate(LocalDate.now().minusDays(1));
        campaignValidator.validate(campaignResource);
    }

    @Test
    public void shouldCallAdjustEndPeriod() {
        campaignValidator.validate(campaignResource);
        Mockito.verify(adjustEndPeriod).execute(campaignResource);
        Mockito.verifyNoMoreInteractions(adjustEndPeriod);
    }
}

