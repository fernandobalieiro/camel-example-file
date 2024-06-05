package com.acme;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class MyProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        final var body = exchange.getIn().getBody(Person.class);
        // You can now do something with the body.
    }
}
