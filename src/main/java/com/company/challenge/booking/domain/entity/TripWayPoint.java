package com.company.challenge.booking.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.Instant;
import java.util.UUID;

/**
 *TripWayPoint Entity to hold data related with TripWayPoint info for booking
 *
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TripWayPoint{

    @Id
    @Type(type = "uuid-char")
    @GeneratedValue(generator = "UUID")
    @ColumnDefault("random_uuid()")
    @GenericGenerator(name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID tripWayPointId;

    @ManyToOne
    @JoinColumn(name="booking_Id")
    private Booking booking;
    private Boolean lastStop;
    private String locality;
    private Double lat;
    private Double lng;

    @CreationTimestamp
    private Instant tripWayPointTimestamp;

}
