package com.company.challenge.booking.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

/**
 *Booking Entity to hold data related with booking
 *
 */

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Booking {


    @Id
    @Column(nullable=false)
    @Type(type = "uuid-char")
    @GeneratedValue(generator = "UUID")
    @ColumnDefault("random_uuid()")
    @GenericGenerator(name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID bookingId;
    private String passengerName;
    private String passengerContactNumber;
    private OffsetDateTime pickupTime;
    private Boolean asap = true;
    private Integer waitingTime;
    private Integer noOfPassengers;
    private BigDecimal price;
    private Integer rating;

    @CreationTimestamp
    private Instant createdOn;

    @UpdateTimestamp
    private Instant lastModifiedOn;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "booking")
    private Set<TripWayPoint> tripWayPoints;
}