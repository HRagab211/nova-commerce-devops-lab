package com.hossam.novacommerce.event;

import com.hossam.novacommerce.config.RabbitMqConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "lab.rabbitmq", name = "enabled", havingValue = "true")
public class RabbitDomainEventPublisher implements DomainEventPublisher {
    private final RabbitTemplate rabbitTemplate;

    public RabbitDomainEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void publish(String routingKey, Object payload) {
        rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE, routingKey, payload);
    }
}
