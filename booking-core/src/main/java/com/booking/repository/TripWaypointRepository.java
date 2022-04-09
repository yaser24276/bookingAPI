package com.booking.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.booking.entity.TripWaypoint;

@Repository
public interface TripWaypointRepository extends CrudRepository<TripWaypoint, Long> {
}
