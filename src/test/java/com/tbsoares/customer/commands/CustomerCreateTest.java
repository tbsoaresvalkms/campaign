package com.tbsoares.customer.commands;

import com.tbsoares.campaign.commands.Converter;
import com.tbsoares.campaign.commands.CreateCampaign;
import com.tbsoares.campaign.converters.CampaignResourceToCampaign;
import com.tbsoares.campaign.exceptions.StartDateAfterEndDateException;
import com.tbsoares.campaign.models.Campaign;
import com.tbsoares.campaign.repositories.CampaignRepository;
import com.tbsoares.campaign.resources.CampaignResource;
import com.tbsoares.campaign.validators.CampaignValidator;
import com.tbsoares.customer.converters.CustomerResourceToCustomer;
import com.tbsoares.customer.models.Customer;
import com.tbsoares.customer.repositories.CustomerRepository;
import com.tbsoares.customer.resources.CustomerResource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.crypto.Cipher;
import java.time.LocalDate;

public class CustomerCreateTest {
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private Converter converterMock;
    @InjectMocks
    private CustomerCreate customerCreate;
    private CustomerResourceToCustomer converter;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        converter = new CustomerResourceToCustomer();
    }

    @Test
    public void shouldCreateCustomer() {
        CustomerResource toCreate = CustomerResource.builder()
                .id(-1L)
                .fullName("Full Name")
                .team("team")
                .build();

        CustomerResource created = CustomerResource.builder()
                .id(1L)
                .fullName("Full Name")
                .team("team")
                .build();


        Customer customerToCreate = convert(toCreate);
        Customer customerCreated = convert(created);

        Mockito.when(converterMock.convert(toCreate, Customer.class))
                .thenReturn(customerToCreate);

        Mockito.when(customerRepository.save(customerToCreate))
                .thenReturn(customerCreated);

        Mockito.when(converterMock.convert(customerCreated, CustomerResource.class))
                .thenReturn(created);

        CustomerResource customerResource = customerCreate.execute(toCreate);

        Assert.assertEquals(created.getId(), customerResource.getId());
        Assert.assertEquals(created.getTeam(), customerResource.getTeam());
        Assert.assertEquals(created.getFullName(), customerResource.getFullName());
    }

    private Customer convert(CustomerResource customerResource) {
        return converter.convert(customerResource);
    }

}
