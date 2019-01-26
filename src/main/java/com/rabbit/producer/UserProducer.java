package com.rabbit.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("userProducer")
public class UserProducer {

    private AmqpTemplate template;

    @Autowired
    public UserProducer(AmqpTemplate template) {
        this.template = template;
    }


    void sendToQueue() {
        String message = "Message to queue Users";
        template.convertAndSend("users", message);
        System.out.println(" [x] Sent '" + message + "'");
    }
}
