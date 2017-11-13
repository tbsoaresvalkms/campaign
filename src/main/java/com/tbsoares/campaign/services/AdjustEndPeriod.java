package com.tbsoares.campaign.services;

import com.tbsoares.campaign.models.Campaign;
import com.tbsoares.campaign.repositories.CampaignRepository;
import com.tbsoares.campaign.resources.CampaignResource;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class AdjustEndPeriod {
    private final CampaignRepository campaignRepository;

    public AdjustEndPeriod(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    public void execute(CampaignResource campaignResource) {
        adjust(campaignResource.getEndDate(), campaignResource.getId());
    }

    private void adjust(LocalDate endDate, Long originalId) {
        Campaign campaignExisted = campaignRepository.findByIdNotAndEndDate(originalId, endDate);

        if (campaignExisted == null) return;

        LocalDate newEndDate = campaignExisted.addOneDayToEndDate();
        adjust(newEndDate, originalId);

        campaignRepository.save(campaignExisted);
    }
}
