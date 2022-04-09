package com.booking.service;


import java.time.Instant;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.booking.entity.Booking;
import com.booking.entity.TripWaypoint;
import com.booking.repository.BookingRepository;
import com.booking.repository.TripWaypointRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookingServiceImpl implements BookingService{

    private final BookingRepository bookingRepository;
    private final TripWaypointRepository tripWaypointRepository;

    public BookingServiceImpl(BookingRepository bookingRepository, TripWaypointRepository tripWaypointRepository) {
        this.bookingRepository = bookingRepository;
        this.tripWaypointRepository = tripWaypointRepository;
    }

    @Override
    public Booking createBooking(Booking booking) {
        Booking saved = null;
        if(booking.getTripWaypoints() != null && !booking.getTripWaypoints().isEmpty()) {
            booking.getTripWaypoints().forEach(this::addStoppingPoint);
        }
        booking.setCreatedOn(Instant.now());
        booking.setLastModifiedOn(Instant.now());
        try {
            saved = bookingRepository.save(booking);
        } catch (Exception e) {
            log.error("failed to create booking", e);
        }
        return saved;
    }

    @Override
    public Booking getBookingById(Long bookingId) {
        return bookingRepository.findById(bookingId).orElse(null);
    }

    @Override
    public void updateBooking(Booking booking, Long bookingId) {
        Optional<Booking> dbBooking = bookingRepository.findById(bookingId);
        if(dbBooking.isEmpty()) {
            log.error("no such booking with id: {}}", bookingId);
            return;
        }
        booking.getTripWaypoints().forEach(this::addStoppingPoint);
        Booking toBeSaved = dbBooking.get();
        toBeSaved.setId(bookingId);
        toBeSaved.setPassengerName(booking.getPassengerName());
        toBeSaved.setPassengerContactNumber(booking.getPassengerContactNumber());
        toBeSaved.setNumberOfPassengers(booking.getNumberOfPassengers());
        toBeSaved.setAsap(booking.isAsap());
        toBeSaved.setPickupTime(booking.getPickupTime());
        toBeSaved.setPrice(booking.getPrice());
        toBeSaved.setRating(booking.getRating());
        toBeSaved.setWaitingTime(booking.getWaitingTime());
        toBeSaved.setLastModifiedOn(Instant.now());
        toBeSaved.setTripWaypoints(booking.getTripWaypoints());
        try {
            bookingRepository.save(toBeSaved);
        } catch (Exception e) {
            log.error("failed to save booking with id {}", toBeSaved.getId(), e);
        }
    }

    @Override
    public void deleteBookingById(Long bookingId) {
        try {
            bookingRepository.deleteById(bookingId);
        } catch (Exception e) {
            log.error("failed to delete booking with id {}", bookingId, e);
        }
    }

    private void addStoppingPoint(TripWaypoint stoppingPoint) {
        final Optional<TripWaypoint> existingStopping = tripWaypointRepository.findById(stoppingPoint.getId());
        if(existingStopping.isEmpty()) {
            tripWaypointRepository.save(stoppingPoint);
        }
    }
}
