package com.booking.rest;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.booking.service.ProducerService;

@Controller
@RequestMapping("/booking")
public class bookingController {

    private final ProducerService producerService;

    public bookingController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping("/add")
    public ResponseEntity addBooking(@RequestBody String message) throws IOException {
        this.producerService.sendMessage(message, "add");
        return ResponseEntity.ok().build();
    }

    @PostMapping("/delete")
    public ResponseEntity deleteBooking(@RequestBody String message) throws IOException {
        this.producerService.sendMessage(message, "delete");
        return ResponseEntity.ok().build();
    }

    @PostMapping("/edit")
    public ResponseEntity editBooking(@RequestBody String message) throws IOException {
        this.producerService.sendMessage(message, "edit");
        return ResponseEntity.ok().build();
    }

}
