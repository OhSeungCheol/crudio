package com.example.demo.rabbitmq;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@ConditionalOnProperty(value = "rabbitmq.enabled", havingValue = "true")
@Component
public class Subscribe {
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(name = "exchange", type = ExchangeTypes.TOPIC),
            value = @Queue(name = "queue"),
            key = "routingKey")
    )
    public void receiver(Object myObject) {
        System.out.println("startReceive");
        System.out.println(myObject);
        System.out.println("endReceive");
    }
}
