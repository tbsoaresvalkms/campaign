package com.tbsoares.campaign.repositories;

import com.tbsoares.campaign.models.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {

    List<Campaign> findAllByEndDateGreaterThanEqual(LocalDate today);

    List<Campaign> findAllByTeamAndEndDateGreaterThanEqual(String team, LocalDate now);

    List<Campaign> findAllByAssociatesEmailAndEndDateGreaterThanEqual(String email, LocalDate now);

    Campaign findByIdAndEndDateGreaterThanEqual(Long id, LocalDate today);

    Campaign findByIdNotAndEndDate(Long originalId, LocalDate endDate);

}
