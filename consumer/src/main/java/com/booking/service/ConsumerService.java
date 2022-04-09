package com.booking.service;

import org.springframework.stereotype.Service;

import com.booking.entity.Booking;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ConsumerService {

    private BookingService bookingService;

    public ConsumerService(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public void createBooking(Booking booking) {
        Booking added = bookingService.createBooking(booking);
        if(added != null) log.info("successfully add booking with id {}", added.getId());
    }

    public void updateBooking(Booking booking) {
        bookingService.updateBooking(booking, booking.getId());
    }

    public void deleteBooking(String bookingId) {
        bookingService.deleteBookingById(Long.valueOf(bookingId));
    }
}
