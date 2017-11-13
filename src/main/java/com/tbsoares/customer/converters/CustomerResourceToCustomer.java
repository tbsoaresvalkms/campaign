package com.tbsoares.customer.converters;

import com.tbsoares.campaign.models.Associate;
import com.tbsoares.campaign.resources.AssociateResource;
import com.tbsoares.customer.models.Customer;
import com.tbsoares.customer.resources.CustomerResource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CustomerResourceToCustomer implements Converter<CustomerResource, Customer> {
    @Override
    public Customer convert(CustomerResource customer) {
        return Customer.builder()
                .id(customer.getId())
                .fullName(customer.getFullName())
                .email(customer.getEmail())
                .birthDate(customer.getBirthDate())
                .team(customer.getTeam())
                .build();
    }
}
