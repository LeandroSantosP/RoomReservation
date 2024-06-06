package org.example;

import org.example.domain.Reservation;
import org.example.repositories.ReservationRepository;
import org.example.repositories.RoomRepository;

import java.time.LocalDateTime;
import java.util.UUID;

public class MakeReservation {
    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;

    public MakeReservation(RoomRepository roomRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.reservationRepository = reservationRepository;
    }

    public Output execute(Input input){
        var room= this.roomRepository.get(input.roomId());
        Reservation reservation = Reservation.create(room.getRoomId(), input.email(), input.checkInDate(), input.checkOutDate());
        reservation.calculate(room);
        this.reservationRepository.persist(reservation);
        var reservationId = this.reservationRepository.get(reservation.getReservationId()).getReservationId();
        return new Output(reservationId);
    }

    public record Input(String email, LocalDateTime checkInDate, LocalDateTime checkOutDate, UUID roomId) {
    }

    public record Output(UUID reservationId) {
    }
}
