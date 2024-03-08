package com.example.proyectou2.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfigBinding {
    public static final String QUEUE_APARTAMENTO = "Apartamento";
    public static final String QUEUE_PAGO = "Pago";
    public static final String DEAD_LETTER_QUEUE = "Dead_Letter_Queue";
    public static final String DEAD_LETTER_EXCHANGE = "Dead_Letter_Exchange";

    @Bean
    public Queue queue1() {
        return QueueBuilder.durable(QUEUE_APARTAMENTO)
                .withArgument("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE)
                .build();
    }

    @Bean
    public Queue queuePago() {
        return QueueBuilder.durable(QUEUE_PAGO)
                .withArgument("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE)
                .build();
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange("direct_exchange");
    }

    @Bean
    public Queue deadLetterQueue() {
        return new Queue(DEAD_LETTER_QUEUE);
    }

    @Bean
    public DirectExchange deadLetterExchange() {
        return new DirectExchange(DEAD_LETTER_EXCHANGE);
    }

    @Bean
    public Binding deadLetterBinding() {
        return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange()).with("dead_letter");
    }

    @Bean
    public Binding binding1(Queue queue1, DirectExchange exchange) {
        return BindingBuilder.bind(queue1).to(exchange).with("routing.Apartamento");
    }

    @Bean
    public Binding bindingPago(Queue queuePago, DirectExchange exchange) {
        return BindingBuilder.bind(queuePago).to(exchange).with("routing.Pago");
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory factory, MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }
}