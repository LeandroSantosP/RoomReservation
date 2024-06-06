package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@ToString
public class Reservation {
    private final UUID reservationId;
    private final UUID roomId;
    private final Email email;
    private final String status;
    private final LocalDateTime checkInDate;
    private final LocalDateTime checkOutDate;
    private long duration;
    private Double price;

    public Reservation(UUID reservationId, UUID roomId, String email, String status, LocalDateTime checkInDate, LocalDateTime checkOutDate, long duration, Double price) {
        this.reservationId = reservationId;
        this.roomId = roomId;
        this.email = new Email(email);
        this.status = status;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.duration = duration;
        this.price = price;
    }

    public static Reservation create(UUID roomId, String email, LocalDateTime checkInDate, LocalDateTime checkOutDate) {
        var reservationId = UUID.randomUUID();
        final long duration = 0;
        final double price = 0;
        final String status = "active";
        return new Reservation(reservationId, roomId, email, status, checkInDate, checkOutDate, duration, price);
    }
    public void calculate(Room room) {
        final var result = PriceCalculatorFactory.create(room.getType()).calculate(this.checkInDate, this.checkOutDate, room.getPrice());
        this.duration = result.duration();
        this.price = result.price();
    }


    public String getEmail(){
        return this.email.getValue();
    }
}
