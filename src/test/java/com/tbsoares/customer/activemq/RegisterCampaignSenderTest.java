package com.tbsoares.customer.activemq;

import com.tbsoares.customer.activemq.Destination.Destination;
import com.tbsoares.customer.activemq.Receiver.RegisterCampaignReceiver;
import com.tbsoares.customer.activemq.Sender.RegisterCampaignSender;
import com.tbsoares.customer.activemq.Sender.Sender;
import com.tbsoares.customer.clients.CampaignClient;
import com.tbsoares.customer.commands.CustomerEnrollCampaign;
import com.tbsoares.customer.exceptions.CampaignClientException;
import com.tbsoares.customer.resources.CustomerResource;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.jms.core.JmsTemplate;

public class RegisterCampaignSenderTest {
    @Mock
    private JmsTemplate jmsTemplate;
    @Mock
    private Sender<CustomerResource> registerCampaignDelaySender;
    @Mock
    private CustomerEnrollCampaign customerEnrollCampaign;
    @InjectMocks
    private RegisterCampaignReceiver campaignReceiver;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldSendCustomerToQueue() throws InterruptedException {
        CustomerResource customerResource = CustomerResource.builder()
                .id(-1L)
                .fullName("Full Name")
                .team("team")
                .build();

        campaignReceiver.receiveMessage(customerResource);
        Mockito.verify(customerEnrollCampaign).execute(customerResource);
    }

    @Test
    public void shouldRequeueWhenEnrollHasError() throws InterruptedException {
        CustomerResource customerResource = CustomerResource.builder()
                .id(-1L)
                .fullName("Full Name")
                .team("team")
                .build();

        Mockito.doThrow(CampaignClientException.class).when(customerEnrollCampaign).execute(customerResource);
        campaignReceiver.receiveMessage(customerResource);
        Mockito.verify(registerCampaignDelaySender).send(customerResource);
    }
}
