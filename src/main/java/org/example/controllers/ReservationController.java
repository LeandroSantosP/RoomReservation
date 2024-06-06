package org.example.controllers;


import org.example.application.GetReservation;
import org.example.application.MakeReservation;
import org.example.dtos.MakeReservationInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    @Autowired
    MakeReservation makeReservation;
    @Autowired
    GetReservation getReservation;
    @PostMapping("/make")
    ResponseEntity<MakeReservation.Output> makeReservation(@RequestBody MakeReservationInput input) {
        System.out.println(input);
        return ResponseEntity.ok(makeReservation.execute(input));
    }

    @GetMapping("/{reservationId}")
    ResponseEntity<GetReservation.Output> getReservation(@PathVariable("reservationId") String reservationId){
        return ResponseEntity.ok(getReservation.execute(UUID.fromString(reservationId)));
    }
}
