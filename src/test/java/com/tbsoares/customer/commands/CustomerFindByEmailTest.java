package com.tbsoares.customer.commands;

import com.tbsoares.campaign.commands.Converter;
import com.tbsoares.campaign.exceptions.AssociateNotFoundException;
import com.tbsoares.customer.converters.CustomerToCustomerResource;
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

public class CustomerFindByEmailTest {
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private Converter converter;
    @InjectMocks
    private CustomerFindByEmail customerFindByEmail;
    private CustomerToCustomerResource customerToCustomerResource;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        customerToCustomerResource = new CustomerToCustomerResource();
    }

    @Test
    public void shouldGetCustomerByEmail() {
        String email = "email@email.com";
        Customer customer = Customer.builder()
                .id(1L)
                .email("email@email.com")
                .build();

        CustomerResource customerResource = toResource(customer);

        Mockito.when(customerRepository.findByEmail(email))
                .thenReturn(customer);

        Mockito.when(converter.convert(customer, CustomerResource.class))
                .thenReturn(customerResource);

        CustomerResource actual = customerFindByEmail.execute(email);

        Assert.assertEquals(customerResource.getId(), actual.getId());
        Assert.assertEquals(customerResource.getEmail(), actual.getEmail());
    }

    @Test
    public void shouldGetNullWhenNoCustomer() {
        String email = "email@email.com";

        CustomerResource actual = customerFindByEmail.execute(email);

        Assert.assertNull(actual);
    }

    private CustomerResource toResource(Customer customer) {
        return customerToCustomerResource.convert(customer);
    }
}
