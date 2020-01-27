package com.company.challenge.booking.service.producer;


import com.company.challenge.booking.domain.dto.BaseDTO;

import java.util.UUID;

public interface BookingProducerService <T extends BaseDTO> {
     T sendMessageToTopicExchange(String key, T dto);
     void sendMessageToTopicExchange(String key, UUID id);
}
