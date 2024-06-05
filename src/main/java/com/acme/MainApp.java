package com.acme;

import org.apache.qpid.jms.JmsConnectionFactory;
import org.apache.camel.main.Main;

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
        main.configure().addRoutesBuilder(new MyRouteBuilder());
        main.run(args);
    }

    private static JmsConnectionFactory connectionFactory() {
        final var connectionFactory = new JmsConnectionFactory("amqp://localhost:5672");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        return connectionFactory;
    }

}

