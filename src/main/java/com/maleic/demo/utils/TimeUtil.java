package com.maleic.demo.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TimeUtil {
    private static final int FIRST_DAY = Calendar.MONDAY;

    public static List<String> getWeekdays() {
        Calendar calendar = Calendar.getInstance();

        List<String> days = new ArrayList<String>();
        setToFirstDay(calendar);
        for (int i = 0; i < 7; i++) {
            days.add(getDay(calendar));
            calendar.add(Calendar.DATE, 1);
        }
        return days;
    }

    private static void setToFirstDay(Calendar calendar) {
        while (calendar.get(Calendar.DAY_OF_WEEK) != FIRST_DAY) {
            calendar.add(Calendar.DATE, -1);
        }
    }

    private static String getDay(Calendar calendar) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return dateFormat.format(calendar.getTime());
    }
}
