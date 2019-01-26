package com.rabbit.producer;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.rabbit.producer")
public class AppConfiguration {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        UserProducer producer = context.getBean("userProducer", UserProducer.class);
        producer.sendToQueue();
    }
}
