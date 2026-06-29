package com.acme;

import com.rabbitmq.client.ConnectionFactory;
import org.apache.camel.main.Main;
import org.apache.qpid.jms.JmsConnectionFactory;

/**
 * A Camel Application
 */
public class MainApp {

    /**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String... args) throws Exception {
        final var main = new Main();
        main.bind("connectionFactory", connectionFactory());
        main.bind("connectionFactoryArtemis", connectionFactoryArtemis());
        main.configure().addRoutesBuilder(new MyRouteBuilder());
        main.run(args);
    }

    private static ConnectionFactory connectionFactory() {
        final var connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        return connectionFactory;
    }

    private static JmsConnectionFactory connectionFactoryArtemis() {
        final var connectionFactory = new JmsConnectionFactory("amqp://localhost:5673");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        return connectionFactory;
    }

}

