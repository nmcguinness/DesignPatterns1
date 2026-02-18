package org.example.utilities;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimeHelper {

    /// <summary>
    /// Formatter used to convert a UTC timestamp into a human-readable string.
    /// Format: HH:mm:ss dd:MM:yy
    /// </summary>
    /// <see cref="DateTimeFormatter"/>
    private static final DateTimeFormatter dateTimeFormat =
            DateTimeFormatter.ofPattern("HH:mm:ss dd:MM:yy");

    /// <summary>
    /// Converts a UTC timestamp expressed as epoch seconds into a formatted date/time string.
    /// </summary>
    /// <param name="utcSeconds">UTC timestamp in epoch seconds.</param>
    /// <returns>A formatted string in the form HH:mm:ss dd:MM:yy.</returns>
    /// <see cref="Instant"/>
    /// <see cref="ZonedDateTime"/>
    public static String utcToString(long utcSeconds) {

        // Convert epoch seconds to an Instant (a point on the timeline)
        Instant instant = Instant.ofEpochSecond(utcSeconds);

        // Attach the UTC timezone to produce a ZonedDateTime
        ZonedDateTime dt = instant.atZone(ZoneOffset.UTC);

        // Format the date/time using the configured pattern
        return dt.format(dateTimeFormat);
    }
}
