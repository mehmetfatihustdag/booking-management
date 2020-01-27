package com.company.challenge.booking.service;

import com.company.challenge.booking.domain.dto.BookingDTO;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface BookingService {

    BookingDTO save(BookingDTO bookingDAO);

    BookingDTO update(BookingDTO bookingDAO);

    void delete(UUID bookingId);

    BookingDTO get(UUID id);

    Page<BookingDTO> getAll(Integer pageNo, Integer pageSize, String sortBy);

}
