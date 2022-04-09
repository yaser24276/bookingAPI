package com.booking.utils;

import com.booking.entity.Booking;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ObjectMapperUtils {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String getBookingAsJson(Booking booking) {
        JavaTimeModule module = new JavaTimeModule();
        objectMapper.registerModule(module);
        ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
        try {
            return ow.writeValueAsString(booking);
        } catch (JsonProcessingException e) {
            log.error("failed to convert booking object to json");
            throw new IllegalArgumentException(e);
        }
    }

    public static Booking getBookingFromJson(String bookingJson) {
        JavaTimeModule module = new JavaTimeModule();
        objectMapper.registerModule(module);
        ObjectReader objectReader = objectMapper.reader().forType(Booking.class);
        try {
            return objectReader.readValue(bookingJson);
        } catch (JsonProcessingException e) {
            log.error("failed to parse booking object from json");
            throw new IllegalArgumentException(e);
        }
    }
}
