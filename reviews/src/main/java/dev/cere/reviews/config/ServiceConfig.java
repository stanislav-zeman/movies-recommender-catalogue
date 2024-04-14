package dev.cere.reviews.config;

import java.util.Map;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
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

    public void declareQueue() {
        boolean durable = true;
        boolean exclusive = false;
        boolean autoDelete = false;

        String queue =
                rabbitAdmin.declareQueue(new Queue(queueName, durable, exclusive, autoDelete));

        DirectExchange exchange = new DirectExchange(queueExchange, durable, autoDelete);

        rabbitAdmin.declareExchange(exchange);

        Binding.DestinationType destinationType = Binding.DestinationType.QUEUE;
        Map<String, Object> arguments = null;

        Binding binding =
                new Binding(queue, destinationType, queueExchange, queueRoutingKey, arguments);

        rabbitAdmin.declareBinding(binding);
    }
}
