package com.acme;

import org.apache.camel.builder.RouteBuilder;

/**
 * A Camel Java DSL Router
 */
public class MyRouteBuilder extends RouteBuilder {

    private final MyProcessor myProcessor = new MyProcessor();

    /**
     * Let's configure the Camel routing rules using Java code...
     */
    public void configure() {

        from("file:src/data?noop=true").id("fromDir")
            .choice()
                .when(xpath("/person/city = 'London'"))
                    .log("${body}")
                    .unmarshal().jacksonXml(Person.class)
                    .process(this.myProcessor)
                    .log("London message: ${body}")
                    .convertBodyTo(String.class)
                    // TODO: Send message to Artemis.
                    .to("???").id("toUK")
                .otherwise()
                    .log("${body}")
                    .unmarshal().jacksonXml(Person.class)
                    .process(this.myProcessor)
                    .log("Other message: ${body}")
                    .convertBodyTo(String.class)
                    // TODO: Send message to Artemis.
                    .to("???").id("toOthers")
            .end()
        ;

        // TODO: Consume from Artemis
        from("???").id("fromUK")
            .log("<<< Message received from London: ${body}");

        from("???").id("fromOthers")
            .log("<<< Message received from Others: ${body}");
    }

}
