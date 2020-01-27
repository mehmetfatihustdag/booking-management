package com.company.challenge.booking.repository;

import com.company.challenge.booking.domain.entity.TripWayPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TripWayPointRepository  extends JpaRepository<TripWayPoint, UUID> {
}
