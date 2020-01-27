package com.company.challenge.booking.repository;


import com.company.challenge.booking.domain.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * @author fatihustdag
 */
@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {

    Optional<Booking> findBookingByBookingId(UUID bookingId);


}
