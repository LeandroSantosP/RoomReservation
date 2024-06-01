package org.example.domain;

import java.time.LocalDateTime;

public abstract  class PriceCalculator {
   public OutPut calculate(LocalDateTime checkInDate, LocalDateTime checkOutDate, Double roomPrice) {
       var duration = this.calculateDistance(checkInDate, checkOutDate);
       var price = duration * roomPrice;
       return new OutPut(duration, price);
   };

    abstract Long calculateDistance(LocalDateTime checkInDate, LocalDateTime checkOutDate);
    record OutPut(Long duration, Double price) {};
}
