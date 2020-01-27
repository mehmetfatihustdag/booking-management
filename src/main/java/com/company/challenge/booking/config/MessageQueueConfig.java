package com.company.challenge.booking.config;

import com.company.challenge.booking.domain.enums.EventType;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;

/**
 *Message Queue Config
 *
 */
@Configuration
public class MessageQueueConfig {


    //Exchanges
    @Bean
    Exchange messageExchange() {
        return new FanoutExchange("message.exchange");
    }

    @Bean
    Exchange bookingExchange() {
        return new DirectExchange("booking.exchange");
    }

    //Queues
    @Bean
    Queue messageAuditQueue() {
        return new Queue("message-audit-queue");
    }

    @Bean
    Queue bookingAddQueue() {
        return new Queue("booking-added-queue");
    }

    @Bean
    Queue bookingEditQueue() {
        return new Queue("booking-edited-queue");
    }

    @Bean
    Queue bookingDeletedQueue() {
        return new Queue("booking-deleted-queue");
    }


    //Bindings
    @Bean
    Binding bookingExchangeBindMessageExchange() {
        return BindingBuilder.bind(bookingExchange()).to(messageExchange()).with("booking.*").noargs();
    }

    @Bean
    Binding messageAuditQueueBindMessageExchange() {
        return BindingBuilder.bind(messageAuditQueue()).to(messageExchange()).with("booking.*").noargs();
    }

    @Bean
    Binding bookingAddQueueBinding() {
        return BindingBuilder.bind(bookingAddQueue()).to(bookingExchange()).with(EventType.ADD.getRountingKey()).noargs();
    }

    @Bean
    Binding bookingEditQueueBinding() {
        return BindingBuilder.bind(bookingEditQueue()).to(bookingExchange()).with(EventType.MODIFY.getRountingKey()).noargs();
    }


    @Bean
    Binding bookingDeleteQueueBinding() {
        return BindingBuilder.bind(bookingDeletedQueue()).to(bookingExchange()).with(EventType.DELETE.getRountingKey()).noargs();
    }



}
