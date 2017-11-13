package com.tbsoares.customer.converters;

import com.tbsoares.campaign.models.Associate;
import com.tbsoares.campaign.resources.AssociateResource;
import com.tbsoares.customer.models.Customer;
import com.tbsoares.customer.resources.CustomerResource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CustomerToCustomerResource implements Converter<Customer, CustomerResource> {
    @Override
    public CustomerResource convert(Customer customer) {
        return CustomerResource.builder()
                .id(customer.getId())
                .fullName(customer.getFullName())
                .email(customer.getEmail())
                .birthDate(customer.getBirthDate())
                .team(customer.getTeam())
                .build();
    }
}
