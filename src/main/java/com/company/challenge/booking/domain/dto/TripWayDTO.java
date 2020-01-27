package com.company.challenge.booking.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TripWayDTO extends BaseDTO {

    private UUID tripWayPointId;
    @JsonIgnore
    private BookingDTO booking;
    private Boolean lastStop;
    private String locality;
    private Double lat;
    private Double lng;

    private Instant tripWayPointTimestamp;

}
