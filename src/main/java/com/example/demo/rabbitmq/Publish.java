package com.example.demo.rabbitmq;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@ConditionalOnProperty(value = "rabbitmq.enabled", havingValue = "true")
@RequiredArgsConstructor
@Component
public class Publish {
    final RabbitTemplate rabbitTemplate;

    @Scheduled(fixedRate = 5000)
    public void sender() {
        System.out.println("startSend");
        rabbitTemplate.convertAndSend("exchange", "routingKey", new Object().toString());
        System.out.println("endSend");
    }
}
