package com.padr.gys.common.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;

public class DateUtil {

    public static boolean isWeekend(LocalDate localDate) {
        DayOfWeek day = DayOfWeek.of(localDate.get(ChronoField.DAY_OF_WEEK));

        return day.equals(DayOfWeek.SUNDAY) || day.equals(DayOfWeek.SATURDAY);
    }
}
