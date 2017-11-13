package com.tbsoares.campaign.resources;

import com.tbsoares.campaign.models.Campaign;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class AssociateResource {
    private Long id;
    private String email;
    private List<Campaign> campaigns;
    private LocalDateTime createdAt;
}
