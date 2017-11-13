package com.tbsoares.customer.services;

import com.tbsoares.campaign.resources.CampaignResource;
import com.tbsoares.customer.commands.*;
import com.tbsoares.customer.exceptions.CustomerNotFoundException;
import com.tbsoares.customer.models.CustomerResponseWrapper;
import com.tbsoares.customer.resources.CustomerResource;
import org.springframework.stereotype.Component;


import java.util.List;

import static com.tbsoares.campaign.utils.Optional.optional;

@Component
public class CustomerService {

    private final CustomerFindByEmail customerFindByEmail;
    private final CustomerCreate customerCreate;
    private final FindAvailableCampaignByCustomer findAvailableCampaignByCustomer;
    private final SendCustomerToQueue sendCustomerToQueue;

    public CustomerService(CustomerFindByEmail customerFindByEmail, CustomerCreate customerCreate, FindAvailableCampaignByCustomer findCampaignsByTeam, FindAvailableCampaignByCustomer findAvailableCampaignByCustomer, SendCustomerToQueue sendCustomerToQueue) {
        this.customerFindByEmail = customerFindByEmail;
        this.customerCreate = customerCreate;
        this.findAvailableCampaignByCustomer = findAvailableCampaignByCustomer;
        this.sendCustomerToQueue = sendCustomerToQueue;
    }


    public CustomerResponseWrapper create(CustomerResource customerResource) {
        return isNewCustomer(customerResource) ? createNewCustomer(customerResource) : customerAlreadyExists(customerResource);
    }

    public void register(String email) {
        optional(customerFindByEmail.execute(email)).
                map(sendCustomerToQueue::execute)
                .orElseThrow(CustomerNotFoundException::new);
    }

    private CustomerResponseWrapper customerAlreadyExists(CustomerResource customerResource) {
        List<CampaignResource> campaigns = findAvailableCampaignByCustomer.execute(customerResource);

        return CustomerResponseWrapper.builder()
                .message("Customer already registered")
                .availableCampaigns(campaigns)
                .build();
    }

    private CustomerResponseWrapper createNewCustomer(CustomerResource customerResource) {
        CustomerResource customerCreated = customerCreate.execute(customerResource);
        register(customerResource.getEmail());
        return CustomerResponseWrapper.builder()
                .message("Customer successfully created")
                .customer(customerCreated)
                .build();
    }

    private boolean isNewCustomer(CustomerResource customerResource) {
        return customerFindByEmail.execute(customerResource.getEmail()) == null;
    }

}
