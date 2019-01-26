package com.rabbit.producer;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {
    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory("localhost");
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setExchange("users-exchange");
        return rabbitTemplate;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public Queue usersQueue1() {
        return new Queue("users-queue-1");
    }

    @Bean
    public Queue usersQueue2() {
        return new Queue("users-queue-2");
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("users-exchange");
    }

    @Bean
    public Binding adultBindingQueue1() {
        return BindingBuilder.bind(usersQueue1()).to(directExchange()).with("adult");
    }

    @Bean
    public Binding youngBindingQueue1() {
        return BindingBuilder.bind(usersQueue1()).to(directExchange()).with("young");
    }

    @Bean
    public Binding adultBindingQueue2() {
        return BindingBuilder.bind(usersQueue2()).to(directExchange()).with("adult");
    }
}
