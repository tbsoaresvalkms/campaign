package com.tbsoares.campaign.repositories;

import com.tbsoares.campaign.models.Associate;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AssociateRepository extends JpaRepository<Associate, Long> {
    Associate findByEmail(String email);

    Boolean existsByEmailAndCampaignsId(String email, Long campaignId);
}
