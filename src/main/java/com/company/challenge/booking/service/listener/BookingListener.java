package com.company.challenge.booking.service.listener;


import com.company.challenge.booking.domain.dto.BookingDTO;
import com.company.challenge.booking.domain.entity.Booking;
import com.company.challenge.booking.domain.mapper.BookingMapper;
import com.company.challenge.booking.exception.BusinessBadRequestException;
import com.company.challenge.booking.repository.BookingRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

import java.beans.FeatureDescriptor;
import java.util.UUID;
import java.util.stream.Stream;

@Component
public class BookingListener {

    private final BookingRepository bookingRepository;

    private final BookingMapper bookingMapper;

    public BookingListener(BookingRepository bookingRepository,
                           BookingMapper bookingMapper){
        this.bookingMapper = bookingMapper;
        this.bookingRepository = bookingRepository;
    }

    @RabbitListener(queues = "booking-added-queue")
    public BookingDTO addBooking(final BookingDTO bookingDTO){
        final Booking savedBooking = bookingRepository.save(bookingMapper.fromDTOToEntity(bookingDTO));
        return bookingMapper.fromEntityToDTO(savedBooking);
    }

    @RabbitListener(queues = "booking-edited-queue")
    public BookingDTO editBooking(final BookingDTO bookingDTO) {
        Booking bookingInDB = bookingRepository.findBookingByBookingId(bookingDTO.getBookingId()).
                orElseThrow(()->new BusinessBadRequestException("Not Found"));

        String[] ignoredProperties = getNullPropertyNames(bookingDTO);
        BeanUtils.copyProperties(bookingDTO, bookingInDB, ignoredProperties);

        final Booking savedBooking = bookingRepository.save(bookingInDB);

        return bookingMapper.fromEntityToDTO(savedBooking);
    }

    @RabbitListener(queues = "booking-deleted-queue")
    public void deleteBooking(UUID bookingId) {
        Booking bookingInDB = bookingRepository.findBookingByBookingId(bookingId).
                orElseThrow(()->new BusinessBadRequestException("Not Found"));
        bookingRepository.delete(bookingInDB);
    }

    private String[] getNullPropertyNames(final BookingDTO bookingDTO) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(bookingDTO);
        return Stream.of(wrappedSource.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null)
                .toArray(String[]::new);
    }




}