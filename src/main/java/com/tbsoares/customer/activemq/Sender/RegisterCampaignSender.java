package com.tbsoares.customer.activemq.Sender;

import com.tbsoares.customer.activemq.Destination.Destination;
import com.tbsoares.customer.models.Customer;
import com.tbsoares.customer.models.CustomerResponseWrapper;
import com.tbsoares.customer.resources.CustomerResource;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class RegisterCampaignSender extends Sender<CustomerResource> {

    private Destination destination;

    public RegisterCampaignSender(JmsTemplate jmsTemplate, Destination destination) {
        super(jmsTemplate);
        this.destination = destination;
    }

    @Override
    public void send(CustomerResource message) {
        super.jmsTemplate.convertAndSend(destination.getRoute(), message);
    }
}
