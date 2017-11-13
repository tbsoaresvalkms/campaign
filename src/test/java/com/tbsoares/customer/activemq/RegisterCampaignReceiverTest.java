package com.tbsoares.customer.activemq;

import com.tbsoares.customer.activemq.Destination.Destination;
import com.tbsoares.customer.activemq.Sender.RegisterCampaignSender;
import com.tbsoares.customer.resources.CustomerResource;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.jms.core.JmsTemplate;

public class RegisterCampaignReceiverTest {
    @Mock
    private Destination destination;
    @Mock
    private JmsTemplate jmsTemplate;
    @InjectMocks
    private RegisterCampaignSender registerCampaignSender;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldSendCustomerToQueue() {
        CustomerResource customerResource = CustomerResource.builder()
                .id(-1L)
                .fullName("Full Name")
                .team("team")
                .build();

        registerCampaignSender.send(customerResource);
        Mockito.verify(destination).getRoute();
        Mockito.verify(jmsTemplate).convertAndSend(destination.getRoute(), customerResource);
    }
}
