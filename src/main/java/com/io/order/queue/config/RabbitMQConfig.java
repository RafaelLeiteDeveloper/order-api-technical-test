package com.io.order.queue.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.io.order.queue.properties.QueueProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class RabbitMQConfig {

    private final QueueProperties properties;
    private static final ObjectMapper mapper = new ObjectMapper();

    @Bean
    public Jackson2JsonMessageConverter messageConverter(){
        mapper.registerModule(new JavaTimeModule());
        return new Jackson2JsonMessageConverter(mapper);
    }

    @Bean
    public Queue queue (){
        return QueueBuilder.durable(properties.getConsumers().getOrderQueue())
                .withArgument("x-dead-letter-exchange", "dead_letter_exchange")
                .withArgument("x-dead-letter-routing-key", "dead_letter_key")
                .build();
    }

}