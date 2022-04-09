package com.booking.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "tripWayPoint")
@Data
public class TripWaypoint {

    @Id
    @Column (name = "tripWayPointId")
    private Long id;
    private String locality;
    private Double longitude;
    private Double latitude;
    @JsonIgnore
    @ManyToMany(mappedBy = "tripWaypoints", fetch = FetchType.LAZY)
    private Set<Booking> bookings = new HashSet<>();
}
