package org.example;

import org.example.domain.Reservation;
import org.example.repositories.ReservationRepository;
import org.example.repositories.RoomRepository;

import java.time.LocalDateTime;
import java.util.UUID;

public class GetReservation {
    private final ReservationRepository reservationRepository;

    public GetReservation(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Output execute(UUID reservationId){
        final Reservation reservation = this.reservationRepository.get(reservationId);
        return new Output(reservation.getReservationId(), reservation.getRoomId(), reservation.getStatus(), reservation.getDuration(), reservation.getPrice(), reservation.getEmail(),reservation.getCheckInDate(), reservation.getCheckOutDate());
    }
    public record Output(UUID reservationId, UUID roomId, String status, Long duration, Double price, String email, LocalDateTime checkInDate, LocalDateTime checkOutDate) {
    }
}
