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
public class BookingEditConsumer {

    private Channel channel;
    private ConsumerService consumerService;

    public BookingEditConsumer(Channel channel, ConsumerService consumerService) {
        this.channel = channel;
        this.consumerService = consumerService;
        try {
            channel.basicConsume(Constants.BOOKING_EDIT_QUEUE, true, (s, delivery) -> {
                String message = new String(delivery.getBody(),"UTF-8");
                log.info("got message for booking edit");
                Booking booking = ObjectMapperUtils.getBookingFromJson(message);
                consumerService.updateBooking(booking);
            }, s -> {});
        } catch (Exception ex) {
            log.error("error on initializing edit consumer",ex);
        }
    }
}
