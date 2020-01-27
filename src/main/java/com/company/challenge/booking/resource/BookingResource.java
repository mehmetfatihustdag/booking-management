package com.company.challenge.booking.resource;

import com.company.challenge.booking.domain.dto.BookingDTO;
import com.company.challenge.booking.exception.BusinessBadRequestException;
import com.company.challenge.booking.service.BookingService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.UUID;

@RestController
@RequestMapping("/api/1.0")
public class BookingResource {

    private final BookingService bookingService;


    public BookingResource(BookingService bookingService){
        this.bookingService = bookingService;
    }

    @PostMapping("/bookings")
    ResponseEntity<BookingDTO> createBooking(@RequestBody BookingDTO bookingDTO) {
        BookingDTO bookingInDB = bookingService.save(bookingDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingInDB);
    }

    @PutMapping(value = "/bookings")
    public ResponseEntity<BookingDTO> updateBooking(@RequestBody BookingDTO bookingDTO) {
        BookingDTO bookingInDB = bookingService.update(bookingDTO);
        // Wait until they are all done
        return ResponseEntity.status(HttpStatus.OK).body(bookingInDB);

    }

    @GetMapping("/bookings")
    Page<BookingDTO> getAllBookings(@RequestParam(defaultValue = "0") Integer pageNo,
                                    @RequestParam(defaultValue = "10") Integer pageSize,
                                    @RequestParam(defaultValue = "createdOn") String sortBy) {
        return bookingService.getAll(pageNo,pageSize,sortBy);
    }

    @GetMapping("/bookings/{id}")
    ResponseEntity<BookingDTO> getBookingById(@PathVariable UUID id) {
        BookingDTO bookingInDB = bookingService.get(id);
        return ResponseEntity.status(HttpStatus.OK).body(bookingInDB);
    }


    @DeleteMapping(value = "/bookings/{id}")
    public ResponseEntity<UUID> deleteBooking(@PathVariable UUID id) {
        bookingService.delete(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

}
