package com.company.challenge.booking.domain.enums;

/**
 Event Type Enums to trigger queue
 */
public enum EventType {
    ADD ("booking.add"),
    DELETE("booking.delete"),
    MODIFY ("booking.modify");

    private String rountingKey;

    EventType(String rountingKey) {
        this.rountingKey = rountingKey;
    }

    public String getRountingKey() {
        return rountingKey;
    }


}
