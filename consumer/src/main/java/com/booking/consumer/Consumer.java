package com.booking.consumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.booking.service.Constants;
import com.booking.service.RabbitMqService;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;

@SpringBootApplication
public class Consumer {

    public static void main(String[] args) {
        try {
            Channel bookingChannel = RabbitMqService.createChannel();
            bookingChannel.exchangeDeclare(Constants.BOOKING_EXCHANGE, BuiltinExchangeType.DIRECT);
            bookingChannel.exchangeDeclare(Constants.MESSAGE_EXCHANGE, BuiltinExchangeType.FANOUT);
            bookingChannel.queueDeclare(Constants.BOOKING_ADD_QUEUE, false, false, false, null);
            bookingChannel.queueDeclare(Constants.BOOKING_DELETE_QUEUE, false, false, false, null);
            bookingChannel.queueDeclare(Constants.BOOKING_EDIT_QUEUE, false, false, false, null);
            bookingChannel.queueDeclare(Constants.MESSAGE_AUDIT_QUEUE, false, false, false, null);
            bookingChannel.queueBind(Constants.BOOKING_ADD_QUEUE, Constants.BOOKING_EXCHANGE, "add");
            bookingChannel.queueBind(Constants.BOOKING_DELETE_QUEUE, Constants.BOOKING_EXCHANGE, "delete");
            bookingChannel.queueBind(Constants.BOOKING_EDIT_QUEUE, Constants.BOOKING_EXCHANGE, "edit");
            bookingChannel.queueBind(Constants.MESSAGE_AUDIT_QUEUE, Constants.MESSAGE_EXCHANGE, "");
            bookingChannel.basicConsume(Constants.BOOKING_ADD_QUEUE, true, (s, delivery) -> {
                  String message = new String(delivery.getBody(),"UTF-8");
                  System.out.println("got message from add "+message);
            }, s -> {});
            bookingChannel.basicConsume(Constants.BOOKING_DELETE_QUEUE, true, (s, delivery) -> {
                String message = new String(delivery.getBody(),"UTF-8");
                System.out.println("got message from delete "+message);
            }, s -> {});
            bookingChannel.basicConsume(Constants.BOOKING_EDIT_QUEUE, true, (s, delivery) -> {
                String message = new String(delivery.getBody(),"UTF-8");
                System.out.println("got message from edit "+message);
            }, s -> {});
            bookingChannel.basicConsume(Constants.MESSAGE_AUDIT_QUEUE, true, (s, delivery) -> {
                String message = new String(delivery.getBody(),"UTF-8");
                System.out.println("got message from audit "+message);
            }, s -> {});
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }
}
