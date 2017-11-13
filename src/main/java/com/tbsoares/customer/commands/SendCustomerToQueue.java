package com.tbsoares.customer.commands;

import com.tbsoares.customer.activemq.Sender.Sender;
import com.tbsoares.customer.resources.CustomerResource;
import org.springframework.stereotype.Component;

@Component
public class SendCustomerToQueue {
    private Sender<CustomerResource> sender;

    public SendCustomerToQueue(Sender<CustomerResource> sender) {
        this.sender = sender;
    }

    public CustomerResource execute(CustomerResource customerResource) {
        sender.send(customerResource);
        return customerResource;
    }
}
