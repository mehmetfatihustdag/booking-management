package com.company.challenge.booking.service.producer;

import com.company.challenge.booking.domain.dto.BaseDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BookingProducerServiceImpl implements BookingProducerService<BaseDTO> {

    private String MESSAGE_EXCHANGE = "message.exchange";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public BaseDTO sendMessageToTopicExchange(String key, BaseDTO bookingDTO) {
       return (BaseDTO)rabbitTemplate.convertSendAndReceive(MESSAGE_EXCHANGE, key, bookingDTO);
    }

    @Override
    public void sendMessageToTopicExchange(String key, UUID id) {
         rabbitTemplate.convertAndSend(MESSAGE_EXCHANGE,key,id);
    }


}
