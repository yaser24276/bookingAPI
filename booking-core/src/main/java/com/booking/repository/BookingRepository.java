package com.booking.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.booking.entity.Booking;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Long> {
}
