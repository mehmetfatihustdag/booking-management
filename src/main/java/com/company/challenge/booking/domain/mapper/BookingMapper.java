package com.company.challenge.booking.domain.mapper;

import com.company.challenge.booking.domain.dto.BookingDTO;
import com.company.challenge.booking.domain.dto.TripWayDTO;
import com.company.challenge.booking.domain.entity.Booking;
import com.company.challenge.booking.domain.entity.TripWayPoint;
import org.mapstruct.Mapper;

/**
 * Mapping Interface
 *
 */
@Mapper(componentModel = "spring")
public interface BookingMapper {

     Booking fromDTOToEntity(BookingDTO dto);

     BookingDTO fromEntityToDTO(Booking entity);

     TripWayPoint fromDTOToEntity(TripWayDTO dto);

     TripWayDTO fromEntityToDTO(TripWayPoint entity);

}
