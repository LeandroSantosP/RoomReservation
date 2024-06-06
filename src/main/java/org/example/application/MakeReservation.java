package org.example.application;

import org.example.domain.Reservation;
import org.example.dtos.MakeReservationInput;
import org.example.repositories.ReservationRepository;
import org.example.repositories.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MakeReservation {
    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;


    public MakeReservation(RoomRepository roomRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.reservationRepository = reservationRepository;
    }

    public Output execute(MakeReservationInput input){
        var room= this.roomRepository.get(input.getRoomId());
        Reservation reservation = Reservation.create(room.getRoomId(), input.getEmail(), input.getCheckInDate(), input.getCheckOutDate());
        reservation.calculate(room);
        this.reservationRepository.persist(reservation);
        var reservationId = this.reservationRepository.get(reservation.getReservationId()).getReservationId();
        return new Output(reservationId);
    }


    public record Output(UUID reservationId) {
    }
}
