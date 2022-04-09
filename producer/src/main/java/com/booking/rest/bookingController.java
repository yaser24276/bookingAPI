package com.booking.rest;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.booking.utils.ObjectMapperUtils;
import com.booking.entity.Booking;
import com.booking.service.ProducerService;

@Controller
@RequestMapping("/booking")
public class bookingController {

    private final ProducerService producerService;

    public bookingController(ProducerService producerService) {
       this.producerService = producerService;
    }

    @PostMapping(value = "/add", consumes = "application/json;charset=UTF-8")
    public ResponseEntity addBooking(@RequestBody @Valid Booking booking) throws IOException {
        producerService.sendMessage(ObjectMapperUtils.getBookingAsJson(booking), "add");
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBooking(@PathVariable String id) throws IOException {
        producerService.sendMessage(id, "delete");
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/edit")
    public ResponseEntity editBooking(@RequestBody @Valid Booking booking) throws IOException {
        producerService.sendMessage(ObjectMapperUtils.getBookingAsJson(booking), "edit");
        return ResponseEntity.ok().build();
    }

}
