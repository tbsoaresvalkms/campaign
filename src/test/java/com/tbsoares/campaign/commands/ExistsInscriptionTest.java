package com.tbsoares.campaign.commands;

import com.tbsoares.campaign.repositories.AssociateRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class ExistsInscriptionTest {

    @Mock
    private AssociateRepository associateRepository;
    @InjectMocks
    private ExistsInscription existsInscription;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldBeTrueWhenExist() {
        Mockito.when(associateRepository.existsByEmailAndCampaignsId("email@email.com", 1L))
                .thenReturn(Boolean.TRUE);
        Boolean result = existsInscription.execute(1L, "email@email.com");
        Assert.assertTrue(result);
    }

    @Test
    public void shouldBeFalseWhenNoExist() {
        Mockito.when(associateRepository.existsByEmailAndCampaignsId("email@email.com", 1L))
                .thenReturn(Boolean.FALSE);
        Boolean result = existsInscription.execute(2L, "email@email.com");
        Assert.assertFalse(result);
    }
}
