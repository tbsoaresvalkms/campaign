package com.tbsoares.campaign.converters;

import com.tbsoares.campaign.converters.AssociateToAssociateResource;
import com.tbsoares.campaign.models.Associate;
import com.tbsoares.campaign.resources.AssociateResource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class AssociateToAssociateResourceTest {

    private AssociateToAssociateResource associateResourceToAssociate;

    @Before
    public void init() {
        associateResourceToAssociate = new AssociateToAssociateResource();
    }

    @Test
    public void shouldConverter() {
        Associate associate = Associate.builder()
                .id(1L)
                .email("email")
                .campaigns(new ArrayList<>())
                .createdAt(LocalDateTime.now())
                .build();

        AssociateResource associateResource = associateResourceToAssociate.convert(associate);
        Assert.assertEquals(associateResource.getId(), associate.getId());
        Assert.assertEquals(associateResource.getEmail(), associate.getEmail());
        Assert.assertEquals(associateResource.getCampaigns(), associate.getCampaigns());
        Assert.assertEquals(associateResource.getCreatedAt(), associate.getCreatedAt());
    }
}
