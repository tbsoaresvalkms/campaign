package com.tbsoares.campaign.commands;

import com.tbsoares.campaign.converters.AssociateToAssociateResource;
import com.tbsoares.campaign.exceptions.AssociateNotFoundException;
import com.tbsoares.campaign.models.Associate;
import com.tbsoares.campaign.repositories.AssociateRepository;
import com.tbsoares.campaign.resources.AssociateResource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class FindByEmailAssociateTest {
    @Mock
    private AssociateRepository associateRepository;
    @Mock
    private Converter converter;
    @InjectMocks
    private FindByEmailAssociate findByEmailAssociate;
    private AssociateToAssociateResource associateToAssociateResource;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        associateToAssociateResource = new AssociateToAssociateResource();
    }

    @Test
    public void shouldGetAssociateByEmail() {
        String email = "email@email.com";
        Associate associate = Associate.builder()
                .id(1L)
                .email("email@email.com")
                .build();

        AssociateResource associateResource = toResource(associate);

        Mockito.when(associateRepository.findByEmail(email))
                .thenReturn(associate);

        Mockito.when(converter.convert(associate, AssociateResource.class))
                .thenReturn(associateResource);

        AssociateResource actual = findByEmailAssociate.execute(email);

        Assert.assertEquals(associateResource.getId(), actual.getId());
        Assert.assertEquals(associateResource.getEmail(), actual.getEmail());
    }

    @Test(expected = AssociateNotFoundException.class)
    public void throwExceptionWhenAssociateNotFound() {
        String email = "email@email.com";
        findByEmailAssociate.execute(email);
    }

    private AssociateResource toResource(Associate associate) {
        return associateToAssociateResource.convert(associate);
    }
}
