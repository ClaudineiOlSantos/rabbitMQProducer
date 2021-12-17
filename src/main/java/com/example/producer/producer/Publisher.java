package com.example.producer.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Publisher {

    @Value("${rabbit.exchange}")
    private String exchange;

    @Value("${rabbit.router1}")
    private String r1;

    @Value("${rabbit.router2}")
    private String r2;

    private final AmqpTemplate amqpTemplate;

    public void sendMessage(final Message message, int num) {

        if (num % 2 == 0)
            amqpTemplate.convertAndSend(exchange, r1, message.withMessage("WARN: apenas um warn"));
        else
            amqpTemplate.convertAndSend(exchange, r2, message.withMessage("ERRO: Deu ruim aqui"));
    }
}
