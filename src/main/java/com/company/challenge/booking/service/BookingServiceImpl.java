package com.company.challenge.booking.service;

import com.company.challenge.booking.domain.dto.BookingDTO;
import com.company.challenge.booking.domain.entity.Booking;
import com.company.challenge.booking.domain.enums.EventType;
import com.company.challenge.booking.domain.mapper.BookingMapper;
import com.company.challenge.booking.exception.BusinessBadRequestException;
import com.company.challenge.booking.repository.BookingRepository;
import com.company.challenge.booking.service.producer.BookingProducerService;
import com.company.challenge.booking.service.producer.BookingProducerServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;


@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    private final BookingMapper bookingMapper;

    private final BookingProducerService bookingProducer;

    public BookingServiceImpl(BookingRepository bookingRepository,
                              BookingMapper bookingMapper,
                              BookingProducerServiceImpl bookingProducer){
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
        this.bookingProducer = bookingProducer;
    }



    @Override
    @Transactional
    public BookingDTO save(final BookingDTO bookingDTO) {
        final BookingDTO resultDTO = (BookingDTO) bookingProducer.sendMessageToTopicExchange(EventType.ADD.getRountingKey(), bookingDTO);
        return resultDTO;
    }

    @Override
    @Transactional
    public BookingDTO update(final BookingDTO bookingDTO) {
        final BookingDTO resultDTO = (BookingDTO) bookingProducer.sendMessageToTopicExchange(EventType.MODIFY.getRountingKey(), bookingDTO);
        return resultDTO;
    }

    @Override
    @Transactional
    public void delete(UUID bookingId) {
            bookingProducer.sendMessageToTopicExchange(EventType.DELETE.getRountingKey(),bookingId);
    }

    @Override
    public BookingDTO get(UUID id) {
        Booking bookingInDB = bookingRepository.findBookingByBookingId(id).
                orElseThrow(()->new BusinessBadRequestException("Not Found"));
        return bookingMapper.fromEntityToDTO(bookingInDB);
    }

    @Override
    public Page<BookingDTO> getAll(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        return bookingRepository.findAll(paging).map(this::convertEntityToDTO);
    }

    private BookingDTO convertEntityToDTO(Booking o) {
        return bookingMapper.fromEntityToDTO(o);
    }


}
