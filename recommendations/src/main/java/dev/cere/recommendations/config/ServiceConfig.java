package dev.cere.recommendations.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.cere.recommendations.api.ReviewDto;
import dev.cere.recommendations.api.ReviewPutDto;
import dev.cere.recommendations.mappers.ReviewMapper;
import dev.cere.recommendations.messaging.MessageActionType;
import dev.cere.recommendations.messaging.ReviewMessage;
import dev.cere.recommendations.service.ReviewService;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "dev.cere.recommendations.data.repository")
@EnableRabbit
public class ServiceConfig {

    private final AmqpAdmin rabbitAdmin;
    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;

    @Value("${messaging.queue.name}")
    private String queueName;

    @Value("${messaging.queue.exchange}")
    private String queueExchange;

    @Value("${messaging.queue.routing-key}")
    private String queueRoutingKey;

    private final ObjectMapper objectMapper;

    public ServiceConfig(
            AmqpAdmin rabbitAdmin, ReviewService reviewService, ReviewMapper reviewMapper) {
        this.rabbitAdmin = rabbitAdmin;
        this.reviewService = reviewService;
        this.reviewMapper = reviewMapper;
        this.objectMapper =
                new ObjectMapper()
                        .configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true)
                        .registerModules();
    }

    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(queueExchange);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(queueRoutingKey);
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

    @RabbitListener(queues = "${messaging.queue.name}")
    public void handleMessage(Message message) throws JsonProcessingException {
        String jsonStringReviewMessage = new String(message.getBody(), StandardCharsets.UTF_8);
        ReviewMessage reviewMessage =
                objectMapper.readValue(jsonStringReviewMessage, ReviewMessage.class);

        if (reviewMessage.getActionType() == MessageActionType.CREATE) {
            ReviewDto reviewDto =
                    objectMapper.readValue(reviewMessage.getJsonDataString(), ReviewDto.class);

            reviewService.create(reviewMapper.mapFromDto(reviewDto));
        } else if (reviewMessage.getActionType() == MessageActionType.UPDATE) {
            ReviewDto reviewDto =
                    objectMapper.readValue(reviewMessage.getJsonDataString(), ReviewDto.class);
            ReviewPutDto reviewPutDto = new ReviewPutDto();
            reviewPutDto.setReview(reviewDto.getReview());
            reviewPutDto.setStars(reviewDto.getStars());

            reviewService.update(reviewDto.getId(), reviewPutDto);
        } else if (reviewMessage.getActionType() == MessageActionType.DELETE) {
            Long reviewId = objectMapper.readValue(reviewMessage.getJsonDataString(), Long.class);

            reviewService.delete(reviewId);
        }
    }
}
