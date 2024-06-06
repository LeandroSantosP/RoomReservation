package org.example.repositories;

import org.example.domain.Reservation;
import org.example.domain.Room;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Repository
public class ReservationRepositoryInMemory implements ReservationRepository {
    Map<UUID, Reservation> reservations = new HashMap<>();
    @Override
    public void persist(Reservation reservation) {
        this.reservations.put(reservation.getReservationId(), reservation);
    }
    @Override
    public Reservation get(UUID reservationId) {
        final Reservation reservation = this.reservations.get(reservationId);
        if (reservation == null){
            throw new RuntimeException("Reservation Not Founded!");
        }
        return new Reservation(reservation.getReservationId(), reservation.getRoomId(), reservation.getEmail(), reservation.getStatus(), reservation.getCheckInDate(), reservation.getCheckOutDate(), reservation.getDuration(), reservation.getPrice());
    }
}
