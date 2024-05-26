package dev.cere.reviews.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "dev.cere.reviews.data.repository")
@EnableRabbit
public class ServiceConfig {

    private final AmqpAdmin rabbitAdmin;

    @Value("${messaging.queue.name}")
    private String queueName;

    @Value("${messaging.queue.exchange}")
    private String queueExchange;

    @Value("${messaging.queue.routing-key}")
    private String queueRoutingKey;

    public ServiceConfig(AmqpAdmin rabbitAdmin) {
        this.rabbitAdmin = rabbitAdmin;
    }
}
