package pl.patrykjava.mychatroom.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ApplicationUtils {

    private static final DateTimeFormatter dateTimeFormatter =
            DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy");

    public static String getTime() {
        return dateTimeFormatter.format(LocalDateTime.now());
    }

}
