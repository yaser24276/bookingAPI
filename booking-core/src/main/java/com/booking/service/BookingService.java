package com.booking.service;

import com.booking.entity.Booking;

public interface BookingService {
    Booking createBooking(Booking booking);
    Booking getBookingById(Long bookingId);
    void updateBooking(Booking booking,
                                Long bookingId);
    void deleteBookingById(Long bookingId);
}
