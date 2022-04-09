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
public class MessageAuditConsumer {

    private Channel channel;

    public MessageAuditConsumer(Channel channel) {
        this.channel = channel;
        try {
            channel.basicConsume(Constants.MESSAGE_AUDIT_QUEUE, true, (s, delivery) -> {
                String message = new String(delivery.getBody(),"UTF-8");
                String routingKey = delivery.getEnvelope().getRoutingKey();
                log.info("got message for booking:\n {} \n, Action: {}", message, routingKey);
            }, s -> {});
        } catch (IOException ex) {
            log.error("error on initializing audit consumer",ex);
        }
    }
}
