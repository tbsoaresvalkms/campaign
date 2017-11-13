package com.tbsoares.customer.activemq.Receiver;

import com.tbsoares.customer.activemq.Sender.Sender;
import com.tbsoares.customer.commands.CustomerEnrollCampaign;
import com.tbsoares.customer.resources.CustomerResource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class RegisterCampaignReceiver implements Receiver<CustomerResource> {

    private final Sender<CustomerResource> registerCampaignDelaySender;
    private final CustomerEnrollCampaign customerEnrollCampaign;

    public RegisterCampaignReceiver(@Qualifier("registerCampaignSender") Sender<CustomerResource> registerCampaignDelaySender, CustomerEnrollCampaign customerEnrollCampaign) {
        this.registerCampaignDelaySender = registerCampaignDelaySender;
        this.customerEnrollCampaign = customerEnrollCampaign;
    }

    @JmsListener(destination = "register-campaignId")
    public void receiveMessage(CustomerResource customer) throws InterruptedException {
        try {
            customerEnrollCampaign.execute(customer);
        } catch (Exception ex) {
            ex.printStackTrace();
            Thread.sleep(1000);
            registerCampaignDelaySender.send(customer);
        }
    }
}
