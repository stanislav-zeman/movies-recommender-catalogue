package dev.cere.reviews.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import dev.cere.reviews.messaging.MessageActionType;
import dev.cere.reviews.messaging.ReviewMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ReviewMessagingService {
    private final RabbitTemplate rabbitTemplate;

    @Value("${messaging.queue.exchange}")
    private String exchange;

    @Value("${messaging.queue.routing-key}")
    private String routingKey;

    private final ObjectMapper objectMapper;

    @Autowired
    public ReviewMessagingService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper =
                new ObjectMapper()
                        .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
                        .findAndRegisterModules();
    }

    public void sendMessage(Object messageObject, MessageActionType actionType) {
        try {
            ReviewMessage reviewMessage =
                    new ReviewMessage(objectMapper.writeValueAsString(messageObject), actionType);
            String message = objectMapper.writeValueAsString(reviewMessage);
            rabbitTemplate.convertAndSend(exchange, routingKey, message);
        } catch (JsonProcessingException e) {
            // TODO: Implement proper logging
            e.printStackTrace();
        }
    }
}
