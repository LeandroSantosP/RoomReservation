import org.example.GetReservation;
import org.example.MakeReservation;
import org.example.domain.Reservation;
import org.example.repositories.ReservationRepositoryInMemory;
import org.example.repositories.RoomRepositoryInMemory;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class MakeReservationTest {

    @Test
    void shouldMakeARoomReservationWithDailyPayment(){
        var roomRepository = new RoomRepositoryInMemory();
        var reservationRepository = new ReservationRepositoryInMemory();
        var makeReservation = new MakeReservation(roomRepository, reservationRepository);
        var inputMakeReservation = new MakeReservation.Input(
                "john@gmail.com",
                LocalDateTime.parse("2023-03-01T10:00:00"),
                LocalDateTime.parse("2023-03-05T10:00:00"),
                UUID.fromString("141154cf-5158-4657-b17c-0a275d2d9545")
        );
        var outputMakeReservation = makeReservation.execute(inputMakeReservation);
        assertNotNull(outputMakeReservation.reservationId());

        final GetReservation getReservation = new GetReservation(reservationRepository);
        final var getReservationOutput = getReservation.execute(outputMakeReservation.reservationId());
        assertNotNull(getReservationOutput);
        assertEquals(UUID.fromString("141154cf-5158-4657-b17c-0a275d2d9545"), getReservationOutput.roomId());
        assertEquals("john@gmail.com", getReservationOutput.email());
        assertEquals("active", getReservationOutput.status());
        assertEquals(4, getReservationOutput.duration());
        assertEquals(400.00, getReservationOutput.price());
    }
}
