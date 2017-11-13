package com.tbsoares.customer.commands;

import com.tbsoares.campaign.commands.Converter;
import com.tbsoares.customer.models.Customer;
import com.tbsoares.customer.repositories.CustomerRepository;
import com.tbsoares.customer.resources.CustomerResource;
import org.springframework.stereotype.Component;

@Component
public class CustomerFindByEmail {
    private final CustomerRepository customerRepository;
    private final Converter converter;

    public CustomerFindByEmail(CustomerRepository customerRepository, Converter converter) {
        this.customerRepository = customerRepository;
        this.converter = converter;
    }

    public CustomerResource execute(String email) {
        return toResource(customerRepository.findByEmail(email));
    }

    private CustomerResource toResource(Customer customer) {
        return converter.convert(customer, CustomerResource.class);
    }
}
