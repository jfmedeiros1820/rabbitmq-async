package com.spring.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

    public static final String QUEUE_MESSAGES = "messages-queue";
    public static final String ROUTING_KEY_MESSAGES = "routing-key";
    public static final String EXCHANGE_MESSAGES = "messages-exchange";
    public static final String QUEUE_DEAD_MESSAGES = "dead-messages-queue";

    @Bean
    Queue messagesQueue() {
        return QueueBuilder.durable(QUEUE_MESSAGES)
                .withArgument("x-dead-letter-exchange", "")
                .withArgument("x-dead-letter-routing-key", QUEUE_DEAD_MESSAGES)
                .withArgument("x-message-ttl", 30000) //if message is not consumed in 30 seconds send to DLQ
                .build();
    }

    @Bean
    Queue deadMessagesQueue() {
        return QueueBuilder.durable(QUEUE_DEAD_MESSAGES).build();
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE_MESSAGES);
    }

    @Bean
    Binding binding(Queue messagesQueue, DirectExchange messagesExchange) {
        return BindingBuilder.bind(messagesQueue).to(messagesExchange).with(ROUTING_KEY_MESSAGES);
    }
}
