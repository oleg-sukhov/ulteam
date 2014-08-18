package ua.vn.os.ulteam.model.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

/**
 * @author os
 */
public class DateUtils {
    private static Random rnd = new Random();

    public static LocalDateTime getRandomDate() {
        int year = rnd.nextInt((2014 - 1990) + 1) + 1990;
        int month = rnd.nextInt((12 - 1) + 1) + 1;
        int day = rnd.nextInt((27 - 1) + 1) + 1;
        int hour = rnd.nextInt((12 - 1) + 1) + 1;
        int minutes = rnd.nextInt((59 - 1) + 1) + 1;
        int second = rnd.nextInt((59 - 1) + 1) + 1;

        return LocalDateTime.of(year, month, day, hour, minutes, second);
    }
}
