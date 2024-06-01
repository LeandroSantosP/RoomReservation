import org.example.domain.Reservation;
import org.example.domain.Room;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReservationTest {
    @Test
    void shouldBeAbleToMakeAReservationInADalyRoom() {
        Room room = new Room(UUID.randomUUID(), "day", 200.0);
        Reservation reservation = Reservation.create(UUID.randomUUID(), "john.doe@gmail.com",
                LocalDateTime.parse("2023-03-01T10:00:00"), LocalDateTime.parse("2023-03-05T10:00:00"));
        reservation.calculate(room);
        assertEquals("john.doe@gmail.com", reservation.getEmail());
        assertEquals("active", reservation.getStatus());
        assertEquals(4, reservation.getDuration());
        assertEquals(800.00, reservation.getPrice());
    }
}
