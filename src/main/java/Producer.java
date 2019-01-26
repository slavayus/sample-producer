import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Producer {
    private final static String EXCHANGE_NAME = "direct_users";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(EXCHANGE_NAME, "direct");

            String message = new Gson().toJson(initUserData(), new TypeToken<List<User>>() {
            }.getType());

            channel.basicPublish(EXCHANGE_NAME, "adult", null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + message + "'");
        }
    }

    private static List<User> initUserData() {
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
}
