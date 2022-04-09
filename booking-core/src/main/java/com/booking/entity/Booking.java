package com.booking.entity;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "booking")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

    @Id
    @Column(name = "bookingId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    private String passengerName;
    @NotBlank
    private String passengerContactNumber;
    private Instant pickupTime;
    private boolean Asap;
    private Long waitingTime;
    @NotNull
    private Integer numberOfPassengers;
    @NotNull
    private Double price;
    private Integer rating;
    private Instant createdOn;
    private Instant lastModifiedOn;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "booking_tripWayPoint",
            joinColumns = { @JoinColumn(name = "bookingId") },
            inverseJoinColumns = { @JoinColumn(name = "tripWayPointId") }
    )
    List<TripWaypoint> tripWaypoints = new ArrayList<>();

    public void setTripWaypoints(List<TripWaypoint> tripWaypoints) {
        this.tripWaypoints = tripWaypoints;
    }
}
