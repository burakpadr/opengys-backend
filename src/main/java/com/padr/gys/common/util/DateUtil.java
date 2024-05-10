package com.padr.gys.common.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

public class DateUtil {

    public static boolean isWeekend(LocalDate localDate) {
        DayOfWeek day = DayOfWeek.of(localDate.get(ChronoField.DAY_OF_WEEK));

        return day.equals(DayOfWeek.SUNDAY) || day.equals(DayOfWeek.SATURDAY);
    }

    public static Long differenceBetween(ChronoUnit chronoUnit, LocalDate firstLocalDate,
            LocalDate secondLocalDate) {
        
        return Math.abs(chronoUnit.between(firstLocalDate, secondLocalDate));
    }
}
