package com.tbsoares.campaign.converters;

import com.tbsoares.campaign.models.Associate;
import com.tbsoares.campaign.resources.AssociateResource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AssociateResourceToAssociate implements Converter<AssociateResource, Associate> {
    @Override
    public Associate convert(AssociateResource associateResource) {
        return Associate.builder()
                .id(associateResource.getId())
                .email(associateResource.getEmail())
                .campaigns(associateResource.getCampaigns())
                .createdAt(associateResource.getCreatedAt())
                .build();
    }
}
