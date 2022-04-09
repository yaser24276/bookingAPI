package com.booking.consumer;

import java.io.IOException;

import org.springframework.stereotype.Service;
import com.booking.service.Constants;
import com.booking.service.ConsumerService;
import com.rabbitmq.client.Channel;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookingDeleteConsumer {

    private Channel channel;
    private ConsumerService consumerService;

    public BookingDeleteConsumer(Channel channel, ConsumerService consumerService) {
        this.channel = channel;
        this.consumerService = consumerService;
        try {
            channel.basicConsume(Constants.BOOKING_DELETE_QUEUE, true, (s, delivery) -> {
                String message = new String(delivery.getBody(),"UTF-8");
                log.info("got booking for delete id: {}", message);
                consumerService.deleteBooking(message);
            }, s -> {});
        } catch (IOException ex) {
            log.error("error on initializing delete consumer",ex);
        }
    }
}
