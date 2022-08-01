package com.tpavlyshyn.fp;

import java.util.Arrays;
import java.util.Optional;

public enum DurationRanges {
    ANY("Any", 0, 999),
    TWO_TO_FIVE_DAYS("2-5", 2, 5),
    THREE_TO_NINE_DAYS("6-9", 6, 9),
    MORE_THAN_TEN("10", 10, 999);

    final String name;
    final Integer minDay;
    final Integer maxDay;

    DurationRanges(String name, Integer minDay, Integer maxDay) {
        this.name = name;
        this.minDay = minDay;
        this.maxDay = maxDay;
    }

    public Integer getMinDay() {return minDay;}

    public Integer getMaxDay() {return maxDay;}

    public static Optional<DurationRanges> findByName(String name) {
        return Arrays.stream(DurationRanges.values())
                .filter(s -> s.name.equals(name))
                .findFirst();
    }
}
