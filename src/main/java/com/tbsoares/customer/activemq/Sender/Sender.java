package com.tbsoares.customer.activemq.Sender;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;


@Component
public abstract class Sender<T> {
    JmsTemplate jmsTemplate;

    public Sender(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public abstract void send(T message);
}

