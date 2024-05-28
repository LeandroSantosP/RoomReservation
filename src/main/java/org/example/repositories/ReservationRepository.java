package org.example.repositories;

import org.example.domain.Reservation;

import java.util.UUID;

public interface ReservationRepository {

    public void persist(Reservation reservation);
    public Reservation get(UUID reservationId);
}
