package com.rabbit.producer;

import com.google.gson.Gson;
import com.rabbit.producer.pojo.User;
import com.rabbit.producer.utils.UserUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("userProducer")
public class UserProducer {
    private AmqpTemplate template;
    private List<User> users;
    private Gson gson;
    @Value("${rabbitmq.routingkey.adult}")
    String adultRoutingKey;
    @Value("${rabbitmq.routingkey.young}")
    String youngRoutingKey;

    @Autowired
    public UserProducer(AmqpTemplate template) {
        this.template = template;
    }

    @Autowired
    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Autowired
    public void setGson(Gson gson) {
        this.gson = gson;
    }

    void adult() {
        System.out.println("Emit as adult");
        template.convertAndSend(adultRoutingKey, gson.toJson(UserUtils.filerAdultUsers(users)));
    }

    void young() {
        System.out.println("Emit as young");
        template.convertAndSend(youngRoutingKey, gson.toJson(UserUtils.filerYoungUsers(users)));
    }
}
