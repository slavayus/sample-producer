package com.rabbit.producer;

import com.google.gson.Gson;
import com.rabbit.producer.pojo.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan("com.rabbit.producer")
public class AppConfiguration {

    @Bean
    Gson gsonProvider() {
        return new Gson();
    }

    @Bean
    List<User> usersProvider() {
        List<User> users = new ArrayList<>();
        users.add(new User("Chris", "Schaefer", 19));
        users.add(new User("Scott", "Tiger", 37));
        users.add(new User("John", "Smith", 55));
        users.add(new User("Peter", "Jackson", 13));
        users.add(new User("Jacky", "Chan", 27));
        users.add(new User("Susan", "Boyle", 41));
        users.add(new User("Tinner", "Turner", 15));
        users.add(new User("Lotus", "Notes", 7));
        return users;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        UserProducer producer = context.getBean("userProducer", UserProducer.class);
        producer.adult();
        producer.young();
    }
}
