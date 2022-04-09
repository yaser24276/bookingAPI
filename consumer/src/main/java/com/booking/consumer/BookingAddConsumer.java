package com.booking.consumer;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.booking.utils.ObjectMapperUtils;
import com.booking.entity.Booking;
import com.booking.service.Constants;
import com.booking.service.ConsumerService;
import com.rabbitmq.client.Channel;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookingAddConsumer {

    private Channel channel;
    private ConsumerService consumerService;
    private BookingAddConsumer instance;

    public BookingAddConsumer(Channel channel, ConsumerService consumerService) {
        this.channel = channel;
        this.consumerService = consumerService;
        try {
            channel.basicConsume(Constants.BOOKING_ADD_QUEUE, true, (s, delivery) -> {
                String message = new String(delivery.getBody(),"UTF-8");
                log.info("got message for booking add");
                Booking booking = ObjectMapperUtils.getBookingFromJson(message);
                consumerService.createBooking(booking);
            }, s -> {});
        } catch (IOException ex) {
            log.error("error on initializing add consumer",ex);
        }
    }
}
