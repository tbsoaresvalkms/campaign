package com.tbsoares.customer.services;

import com.tbsoares.campaign.resources.CampaignResource;
import com.tbsoares.customer.commands.CustomerCreate;
import com.tbsoares.customer.commands.CustomerFindByEmail;
import com.tbsoares.customer.commands.FindAvailableCampaignByCustomer;
import com.tbsoares.customer.commands.SendCustomerToQueue;
import com.tbsoares.customer.exceptions.CustomerNotFoundException;
import com.tbsoares.customer.models.CustomerResponseWrapper;
import com.tbsoares.customer.resources.CustomerResource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

public class CustomerServiceTest {
    @InjectMocks
    private CustomerService customerService;
    @Mock
    private CustomerCreate customerCreate;
    @Mock
    private CustomerFindByEmail customerFindByEmail;
    @Mock
    private FindAvailableCampaignByCustomer findAvailableCampaignByCustomer;
    @Mock
    private SendCustomerToQueue sendCustomerToQueue;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldGetAvailableCampaignWhenCustomerAlreadyRegistered() {
        CustomerResource customerResource = CustomerResource.builder()
                .fullName("Full Name")
                .email("customer1@email.com")
                .team("team 1")
                .build();
        CampaignResource campaignOne = CampaignResource.builder().name("campaign 1").build();
        CampaignResource campaignTwo = CampaignResource.builder().name("campaign 2").build();
        List<CampaignResource> campaignList = Arrays.asList(campaignOne, campaignTwo);

        Mockito.when(customerFindByEmail.execute("customer1@email.com"))
                .thenReturn(customerResource);

        Mockito.when(findAvailableCampaignByCustomer.execute(customerResource))
                .thenReturn(campaignList);

        CustomerResponseWrapper responseWrapper = customerService.create(customerResource);
        List<CampaignResource> campaigns = responseWrapper.getAvailableCampaigns();
        Assert.assertEquals(2, campaigns.size());
        Assert.assertEquals(campaignOne.getName(), campaigns.get(0).getName());
        Assert.assertEquals(campaignTwo.getName(), campaigns.get(1).getName());
    }

    @Test(expected = CustomerNotFoundException.class)
    public void throwExceptionCustomerNotFound() {
        customerService.register("customer1@email.com");
    }

}
