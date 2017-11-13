package com.tbsoares.customer.converters;

import com.tbsoares.campaign.converters.AssociateResourceToAssociate;
import com.tbsoares.campaign.models.Associate;
import com.tbsoares.campaign.resources.AssociateResource;
import com.tbsoares.customer.models.Customer;
import com.tbsoares.customer.resources.CustomerResource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class CustomerResourceToCustomerTest {

    private CustomerResourceToCustomer customerResourceToCustomer;

    @Before
    public void init() {
        customerResourceToCustomer = new CustomerResourceToCustomer();
    }

    @Test
    public void shouldConverter() {
        CustomerResource customerResource = CustomerResource.builder()
                .id(1L)
                .fullName("full name")
                .email("email")
                .birthDate(LocalDate.now())
                .team("team 1")
                .build();

        Customer customer = customerResourceToCustomer.convert(customerResource);
        Assert.assertEquals(customerResource.getId(), customer.getId());
        Assert.assertEquals(customerResource.getEmail(), customer.getEmail());
        Assert.assertEquals(customerResource.getTeam(), customer.getTeam());
        Assert.assertEquals(customerResource.getBirthDate(), customer.getBirthDate());
        Assert.assertEquals(customerResource.getFullName(), customer.getFullName());
    }
}
