package com.hossam.novacommerce.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnMissingBean(DomainEventPublisher.class)
public class LoggingDomainEventPublisher implements DomainEventPublisher {
    private static final Logger log = LoggerFactory.getLogger(LoggingDomainEventPublisher.class);

    @Override
    public void publish(String routingKey, Object payload) {
        log.info("Event skipped because RabbitMQ publishing is disabled. routingKey={}, payload={}", routingKey, payload);
    }
}
