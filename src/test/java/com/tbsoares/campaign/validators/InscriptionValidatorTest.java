package com.tbsoares.campaign.validators;

import com.tbsoares.campaign.commands.ExistsInscription;
import com.tbsoares.campaign.exceptions.AlreadyRegisteredException;
import com.tbsoares.campaign.models.Inscription;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


public class InscriptionValidatorTest {

    @InjectMocks
    private InscriptionValidator inscriptionValidator;
    @Mock
    private ExistsInscription existsInscription;
    private Inscription inscription;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        inscription = Inscription.builder()
                .campaignId(1L)
                .email("email@email.com")
                .build();
    }

    @Test
    public void shouldBeSameObject() {
        Inscription actual = inscriptionValidator.validate(inscription);
        Assert.assertEquals(inscription, actual);
    }

    @Test
    public void shouldLowerCaseEmail() {
        inscription.setEmail("EMAIL@EMAIL.COM");
        Inscription actual = inscriptionValidator.validate(inscription);
        Assert.assertEquals("email@email.com", actual.getEmail());
    }

    @Test(expected = AlreadyRegisteredException.class)
    public void throwExceptionWhenAlreadyRegistered() {
        Mockito.when(existsInscription.execute(1L, "email@email.com"))
                .thenReturn(true);
        inscriptionValidator.validate(inscription);
    }
}
