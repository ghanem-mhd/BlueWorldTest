package com.example.mhd_ghanem.blueworldtest.utilities;

import android.annotation.SuppressLint;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateManager {

    private static DateTimeFormatter timeOnlyHoursMinutes = DateTimeFormat.forPattern("HH:mm");
    private static DateTimeFormatter dayMonth = DateTimeFormat.forPattern("MMM dd");

    public static String getDateAsTme(Date date){
        return timeOnlyHoursMinutes.print(date.getTime());
    }

    public static boolean isTomorrow(Date time) {
        return LocalDate.now().plusDays(1).compareTo(new LocalDate(time)) == 0;
    }

    public static String getTomorrowAsDateMonth(){
        return dayMonth.print(LocalDate.now().plusDays(1));
    }
}
