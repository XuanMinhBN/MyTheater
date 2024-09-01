package org.xumin.mytheater.bean;

import java.time.LocalDate;
import java.time.LocalTime;

public class TimeUtils {
    public boolean isAfter(LocalTime time1, LocalTime time2) {
        return time1.isAfter(time2);
    }

    public boolean equals(LocalTime time1, LocalTime time2) {
        return time1.equals(time2);
    }

    public LocalDate today() {
        return LocalDate.now();
    }

    public LocalTime now() {
        return LocalTime.now();
    }

    // Kiểm tra xem ngày có phải là ngày trong tương lai hay không
    public boolean isFutureDate(LocalDate date) {
        return date.isAfter(LocalDate.now());
    }

    // Kiểm tra xem ngày có phải là ngày hiện tại hay không
    public boolean isToday(LocalDate date) {
        return date.isEqual(LocalDate.now());
    }

    // Kiểm tra xem thời gian có phải là trong tương lai hay không
    public boolean isFutureTime(LocalTime time) {
        return time.isAfter(LocalTime.now());
    }
}
