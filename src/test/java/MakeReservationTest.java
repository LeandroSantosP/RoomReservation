import org.example.application.GetReservation;
import org.example.application.MakeReservation;
import org.example.dtos.MakeReservationInput;
import org.example.repositories.ReservationRepositoryInMemory;
import org.example.repositories.RoomRepositoryInMemory;
import org.junit.jupiter.api.Disabled;
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
        var inputMakeReservation = new MakeReservationInput(
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
        assertEquals(4000.00, getReservationOutput.price());
    }

    @Test
    void shouldMakeARoomReservationWithHourPayment(){
        var roomRepository = new RoomRepositoryInMemory();
        var reservationRepository = new ReservationRepositoryInMemory();
        var makeReservation = new MakeReservation(roomRepository, reservationRepository);
        var inputMakeReservation = new MakeReservationInput(
                "john2@gmail.com",
                LocalDateTime.parse("2023-03-05T10:00:00"),
                LocalDateTime.parse("2023-03-05T12:00:00"),
                UUID.fromString("6606e3f1-d486-46d6-9aa3-5223c3514432")
        );
        var outputMakeReservation = makeReservation.execute(inputMakeReservation);
        assertNotNull(outputMakeReservation.reservationId());
        final GetReservation getReservation = new GetReservation(reservationRepository);
        final var getReservationOutput = getReservation.execute(outputMakeReservation.reservationId());
        assertNotNull(getReservationOutput);
        assertEquals(UUID.fromString("6606e3f1-d486-46d6-9aa3-5223c3514432"), getReservationOutput.roomId());
        assertEquals("john2@gmail.com", getReservationOutput.email());
        assertEquals("active", getReservationOutput.status());
        assertEquals(2, getReservationOutput.duration());
        assertEquals(200.00, getReservationOutput.price());
    }
}
