package org.example.domain;

import java.time.Duration;
import java.time.LocalDateTime;

class DayPriceCalculator extends PriceCalculator {
    @Override
    Long calculateDistance(LocalDateTime checkInDate, LocalDateTime checkOutDate) {
        return Math.abs(Duration.between(checkOutDate, checkInDate).toDays());
    }
}

class HourPriceCalculator extends PriceCalculator {
    @Override
    Long calculateDistance(LocalDateTime checkInDate, LocalDateTime checkOutDate) {
        return Math.abs(Duration.between(checkOutDate, checkInDate).toHours());
    }
}

public class PriceCalculatorFactory {
    public static PriceCalculator create(String type) {
        if (type.equals("day")) return new DayPriceCalculator();
        if (type.equals("hour")) return new HourPriceCalculator();
        throw new RuntimeException();
    }
}