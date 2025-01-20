package com.aruizmonta.forohubchallenge.domain.dto.utils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class FormatInstant {
    private static final String FORMAT = "dd.MM.yyyy";

    public static String toDateTime(Instant instant) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT)
                .withZone(ZoneId.systemDefault());
        return formatter.format(instant);
    }
}
