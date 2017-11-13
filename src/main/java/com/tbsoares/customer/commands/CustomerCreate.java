package com.tbsoares.customer.commands;

import com.tbsoares.campaign.commands.Converter;
import com.tbsoares.customer.models.Customer;
import com.tbsoares.customer.repositories.CustomerRepository;
import com.tbsoares.customer.resources.CustomerResource;
import org.springframework.stereotype.Component;

@Component
public class CustomerCreate {
    private final CustomerRepository customerRepository;
    private final Converter converter;

    public CustomerCreate(CustomerRepository customerRepository, Converter converter) {
        this.customerRepository = customerRepository;
        this.converter = converter;
    }

    public CustomerResource execute(CustomerResource customerResource) {
        return toResource(customerRepository.save(toEntity(customerResource)));
    }

    private CustomerResource toResource(Customer customer) {
        return converter.convert(customer, CustomerResource.class);
    }

    private Customer toEntity(CustomerResource customerResource) {
        return converter.convert(customerResource, Customer.class);
    }
}
