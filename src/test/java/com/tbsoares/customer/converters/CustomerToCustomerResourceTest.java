package com.tbsoares.customer.converters;

import com.tbsoares.customer.models.Customer;
import com.tbsoares.customer.resources.CustomerResource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class CustomerToCustomerResourceTest {

    private CustomerToCustomerResource customerToCustomerResource;

    @Before
    public void init() {
        customerToCustomerResource = new CustomerToCustomerResource();
    }

    @Test
    public void shouldConverter() {
        Customer customer = Customer.builder()
                .id(1L)
                .fullName("full name")
                .email("email")
                .birthDate(LocalDate.now())
                .team("team 1")
                .build();

        CustomerResource customerResource = customerToCustomerResource.convert(customer);
        Assert.assertEquals(customer.getId(), customerResource.getId());
        Assert.assertEquals(customer.getEmail(), customerResource.getEmail());
        Assert.assertEquals(customer.getTeam(), customerResource.getTeam());
        Assert.assertEquals(customer.getBirthDate(), customerResource.getBirthDate());
        Assert.assertEquals(customer.getFullName(), customerResource.getFullName());
    }
}
