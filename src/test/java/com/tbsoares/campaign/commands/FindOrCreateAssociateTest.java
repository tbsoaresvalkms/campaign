package com.tbsoares.campaign.commands;

import com.tbsoares.campaign.models.Associate;
import com.tbsoares.campaign.repositories.AssociateRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class FindOrCreateAssociateTest {
    @Mock
    private AssociateRepository associateRepository;
    @InjectMocks
    private FindOrCreateAssociate findOrCreateAssociate;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldGetAssociate() {
        String email = "email@email.com";
        Associate associateRegistered = Associate.builder()
                .id(1L)
                .email("email@email.com")
                .build();

        Mockito.when(associateRepository.findByEmail(email))
                .thenReturn(associateRegistered);

        Associate associate = findOrCreateAssociate.execute(email);

        Assert.assertEquals(associateRegistered.getId(), associate.getId());
        Assert.assertEquals(associateRegistered.getEmail(), associate.getEmail());
    }

    @Test
    public void shouldCreateAssociate() {
        String email = "email@email.com";

        Associate associate = findOrCreateAssociate.execute(email);

        Assert.assertNull(associate.getId());
        Assert.assertEquals(email, associate.getEmail());
    }
}

