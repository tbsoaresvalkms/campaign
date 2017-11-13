package com.tbsoares.campaign.converters;

import com.tbsoares.campaign.models.Associate;
import com.tbsoares.campaign.resources.AssociateResource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AssociateToAssociateResource implements Converter<Associate, AssociateResource> {
    @Override
    public AssociateResource convert(Associate associate) {
        return AssociateResource.builder()
                .id(associate.getId())
                .email(associate.getEmail())
                .campaigns(associate.getCampaigns())
                .createdAt(associate.getCreatedAt())
                .build();
    }
}
