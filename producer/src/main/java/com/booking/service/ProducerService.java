package com.booking.service;


import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Service;

import com.rabbitmq.client.Channel;

@Service
public class ProducerService {
    private Channel channel;

    public ProducerService(Channel channel) {
        this.channel = channel;
    }

    public void sendMessage(String message, String route) throws IOException {
        channel.basicPublish(Constants.MESSAGE_EXCHANGE, route, null, message.getBytes(StandardCharsets.UTF_8));
    }

}
