package com.booking.config;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.booking.service.Constants;
import com.booking.service.RabbitMqService;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;

@Configuration
public class ConnectionConfig {

    @Bean
    public Channel getMqClient() {
        Channel channel = null;
        try {
            channel = RabbitMqService.createChannel();
            channel.queueDeclare(Constants.BOOKING_ADD_QUEUE, false, false, false, null);
            channel.queueDeclare(Constants.BOOKING_DELETE_QUEUE, false, false, false, null);
            channel.queueDeclare(Constants.BOOKING_EDIT_QUEUE, false, false, false, null);
            channel.queueDeclare(Constants.MESSAGE_AUDIT_QUEUE, false, false, false, null);
            channel.exchangeDeclare(Constants.MESSAGE_EXCHANGE, BuiltinExchangeType.FANOUT);
            channel.exchangeDeclare(Constants.BOOKING_EXCHANGE, BuiltinExchangeType.DIRECT);
            channel.exchangeBind(Constants.BOOKING_EXCHANGE, Constants.MESSAGE_EXCHANGE,"add");
            channel.exchangeBind(Constants.BOOKING_EXCHANGE, Constants.MESSAGE_EXCHANGE,"delete");
            channel.exchangeBind(Constants.BOOKING_EXCHANGE, Constants.MESSAGE_EXCHANGE,"edit");
            channel.queueBind(Constants.BOOKING_ADD_QUEUE, Constants.BOOKING_EXCHANGE, "add");
            channel.queueBind(Constants.BOOKING_DELETE_QUEUE, Constants.BOOKING_EXCHANGE, "delete");
            channel.queueBind(Constants.BOOKING_EDIT_QUEUE, Constants.BOOKING_EXCHANGE, "edit");
            channel.queueBind(Constants.MESSAGE_AUDIT_QUEUE, Constants.MESSAGE_EXCHANGE, "");
            return channel;
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
        return channel;
    }
}
