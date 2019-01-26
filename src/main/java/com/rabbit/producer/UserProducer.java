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

    void adult() {
        System.out.println("Emit as adult");
        template.convertAndSend("adult", "Adult");
    }

    void young() {
        System.out.println("Emit as young");
        template.convertAndSend("young", "Young");
    }
}
