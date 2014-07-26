package ua.vn.os.ulteam.model.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author os
 */
public class DateUtils {
    private static Random rnd = new Random();
    private static final String DATE_FORMAT = "yyyy-mm-dd hh:mm:ss";
    private static DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

    private static String getRandomDateAsString() {
        int year = rnd.nextInt((2014 - 1990) + 1) + 1990;
        int month = rnd.nextInt((12 - 1) + 1) + 1;
        int day = rnd.nextInt((27 - 1) + 1) + 1;
        int hour = rnd.nextInt((12 - 1) + 1) + 1;
        int minutes = rnd.nextInt((60 - 1) + 1) + 1;
        int second = rnd.nextInt((60 - 1) + 1) + 1;

        StringBuilder dataBuilder = new StringBuilder();
        dataBuilder.append(year).append('-')
                .append(month < 10 ? '0' + month : month).append('-')
                .append(day < 10 ? '0' + day   : day).append(' ')
                .append(hour < 10 ? '0' + hour : hour).append(':')
                .append(minutes < 10 ? '0' + minutes : minutes).append(':')
                .append(second < 10 ? '0' + second : second);

        return dataBuilder.toString();
    }

    public static Date getRandomDate() {
        Date randomDate = null;

        try {
            randomDate = dateFormat.parse(getRandomDateAsString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return randomDate;
    }

    public static long getRandomTime() {
        return getRandomDate().getTime();
    }
}
