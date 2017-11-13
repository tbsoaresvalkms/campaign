package com.tbsoares.customer.commands;

import com.tbsoares.customer.activemq.Sender.Sender;
import com.tbsoares.customer.resources.CustomerResource;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class SendCustomerToQueueTest {
    @Mock
    private Sender<CustomerResource> sender;
    @InjectMocks
    private SendCustomerToQueue sendCustomerToQueue;

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

        sendCustomerToQueue.execute(customerResource);

        Mockito.verify(sender).send(customerResource);
        Mockito.verifyNoMoreInteractions(sender);
    }
}
