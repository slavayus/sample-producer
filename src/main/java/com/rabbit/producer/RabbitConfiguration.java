package com.rabbit.producer;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:/META-INF/rabbit/rabbit-mq.properties")
public class RabbitConfiguration {
    @Value("${rabbitmq.host}")
    String host;
    @Value("${rabbitmq.port}")
    int port;
    @Value("${rabbitmq.queue.users.1}")
    String usersQueue1;
    @Value("${rabbitmq.queue.users.2}")
    String usersQueue2;
    @Value("${rabbitmq.exchange.users}")
    String usersExchange;

    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory(host, port);
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setExchange(usersExchange);
        return rabbitTemplate;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public Queue usersQueue1() {
        return new Queue(usersQueue1);
    }

    @Bean
    public Queue usersQueue2() {
        return new Queue(usersQueue2);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(usersExchange);
    }

    @Bean
    public Binding adultBindingQueue1(@Value("${rabbitmq.routingkey.adult}") String routingKey) {
        return BindingBuilder.bind(usersQueue1()).to(directExchange()).with(routingKey);
    }

    @Bean
    public Binding youngBindingQueue1(@Value("${rabbitmq.routingkey.adult}") String routingKey) {
        return BindingBuilder.bind(usersQueue1()).to(directExchange()).with(routingKey);
    }

    @Bean
    public Binding adultBindingQueue2(@Value("${rabbitmq.routingkey.adult}") String routingKey) {
        return BindingBuilder.bind(usersQueue2()).to(directExchange()).with(routingKey);
    }
}
