package com.hossam.novacommerce.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "lab.rabbitmq", name = "enabled", havingValue = "true")
public class RabbitMqConfig {
    public static final String EXCHANGE = "nova.events";
    public static final String ORDER_QUEUE = "nova.order.events";
    public static final String ORDER_ROUTING_KEY = "order.created";

    @Bean
    DirectExchange novaExchange() {
        return new DirectExchange(EXCHANGE, true, false);
    }

    @Bean
    Queue orderEventsQueue() {
        return QueueBuilder.durable(ORDER_QUEUE).build();
    }

    @Bean
    Binding orderEventsBinding(Queue orderEventsQueue, DirectExchange novaExchange) {
        return BindingBuilder.bind(orderEventsQueue).to(novaExchange).with(ORDER_ROUTING_KEY);
    }
}
