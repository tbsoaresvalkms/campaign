package com.tbsoares.campaign.validators;

import com.tbsoares.campaign.exceptions.StartDateAfterEndDateException;
import com.tbsoares.campaign.resources.CampaignResource;
import com.tbsoares.campaign.services.AdjustEndPeriod;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CampaignValidator {

    private final AdjustEndPeriod adjustEndPeriod;

    public CampaignValidator(AdjustEndPeriod adjustEndPeriod) {
        this.adjustEndPeriod = adjustEndPeriod;
    }

    public CampaignResource validate(CampaignResource campaignResource) {

        upperCaseTeam(campaignResource);
        checkStartDateLessThanEqualEndDate(campaignResource);
        adjustEndPeriod.execute(campaignResource);

        return campaignResource;
    }

    private void upperCaseTeam(CampaignResource campaignResource) {
        campaignResource.toUpperCaseTeam();
    }

    private void checkStartDateLessThanEqualEndDate(CampaignResource campaignResource) {
        LocalDate startDate = campaignResource.getStartDate();
        LocalDate endDate = campaignResource.getEndDate();

        if (startDate.isAfter(endDate)) throw new StartDateAfterEndDateException();
    }

}
