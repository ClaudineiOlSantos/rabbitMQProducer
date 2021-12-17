package com.example.producer.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@EnableRabbit
@Configuration
public class RabbitConfig {

    @Value("${rabbit.host}")
    private String host;

    @Value("${rabbit.user}")
    private String userName;

    @Value("${rabbit.pass}")
    private String password;

    @Value("${rabbit.exchange}")
    private String exchange;

    @Value("${rabbit.queue1}")
    private String q1;

    @Value("${rabbit.queue2}")
    private String q2;

    @Value("${rabbit.router1}")
    private String r1;

    @Value("${rabbit.router2}")
    private String r2;


    @Bean
    @Primary
    public ConnectionFactory rabbitConnectionFactory() {
        final CachingConnectionFactory factory = new CachingConnectionFactory();

        factory.setHost(host);
        factory.setUsername(userName);
        factory.setPassword(password);

        return factory;
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    public AmqpTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);

        rabbitTemplate.setMessageConverter(jsonMessageConverter());

        return rabbitTemplate;
    }

    @Bean
    public TopicExchange topic() {
        return new TopicExchange(exchange);
    }

    @Bean
    public Queue q1(){
        return new Queue(q1);
    }

    @Bean
    public Queue q2(){
        return new Queue(q2);
    }

    @Bean
    public Binding bindingQ1(){

        return BindingBuilder.bind(q1()).to(topic()).with(r1);
    }

    @Bean
    public Binding bindingQ2(){

        return BindingBuilder.bind(q2()).to(topic()).with(r2);
    }

}
