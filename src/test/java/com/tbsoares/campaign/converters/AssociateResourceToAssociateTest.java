package com.tbsoares.campaign.converters;

import com.tbsoares.campaign.converters.AssociateResourceToAssociate;
import com.tbsoares.campaign.models.Associate;
import com.tbsoares.campaign.resources.AssociateResource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class AssociateResourceToAssociateTest {

    private AssociateResourceToAssociate associateResourceToAssociate;

    @Before
    public void init() {
        associateResourceToAssociate = new AssociateResourceToAssociate();
    }

    @Test
    public void shouldConverter() {
        AssociateResource associateResource = AssociateResource.builder()
                .id(1L)
                .email("email")
                .campaigns(new ArrayList<>())
                .createdAt(LocalDateTime.now())
                .build();

        Associate associate = associateResourceToAssociate.convert(associateResource);
        Assert.assertEquals(associateResource.getId(), associate.getId());
        Assert.assertEquals(associateResource.getEmail(), associate.getEmail());
        Assert.assertEquals(associateResource.getCampaigns(), associate.getCampaigns());
        Assert.assertEquals(associateResource.getCreatedAt(), associate.getCreatedAt());
    }
}
