package org.example.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
@ToString
public class Reservation {
    private final UUID reservationId;
    private final UUID roomId;
    private final String email;
    private final String status;
    private final LocalDateTime checkInDate;
    private final LocalDateTime checkOutDate;
    private long duration;
    private Double price;

    public static Reservation create(UUID roomId, String email,LocalDateTime checkInDate, LocalDateTime checkOutDate) {
        var reservationId = UUID.randomUUID();
        final long duration = 0;
        final double price = 0;
        final String status = "active";
        return new Reservation(reservationId, roomId, email, status, checkInDate, checkOutDate, duration, price);
    }
    public void calculate(Room room) {
        this.duration = Math.abs(Duration.between(this.checkOutDate, this.checkInDate).toDays());
        this.price = this.duration * room.getPrice();
    }
}
