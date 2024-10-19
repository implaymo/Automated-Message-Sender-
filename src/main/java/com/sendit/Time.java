package com.sendit;

import com.google.api.client.util.DateTime;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.TimeZone;

public class Time {

    public static ZonedDateTime convertToZonedDateTime(DateTime googleDateTime) {
        Instant instant = Instant.ofEpochMilli(googleDateTime.getValue());
        return ZonedDateTime.ofInstant(instant, TimeZone.getDefault().toZoneId());
    }

    public static void compareCurrentTimeAndEventTime(ZonedDateTime googleEventDateTime) {
        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime nowTimePlus24Hours = now.plusHours(24);
        if (googleEventDateTime.isAfter(now) && googleEventDateTime.isBefore(nowTimePlus24Hours)) {
            System.out.println("Event is in less than 24 hours");
        } else {
            System.out.println("Event is still far away.");
        }
    }

}
