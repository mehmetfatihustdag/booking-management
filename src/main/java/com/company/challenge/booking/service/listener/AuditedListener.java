package com.company.challenge.booking.service.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuditedListener {

    @RabbitListener(queues = "message-audit-queue")
    public void auditedLogs(GenericMessage message) {
        log.info("Audit Listener : " + message);
    }
}
