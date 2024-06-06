package org.example.dtos;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class MakeReservationInput {

    @NotBlank(message = "The email is required field!")
    @Email(message = "Invalid Email!")
    private String email;

    @NotNull(message = "The checkInDate is required field!")
    private LocalDateTime checkInDate;

    @NotNull(message = "The checkOutDate is required field!")
    private LocalDateTime checkOutDate;

    @NotNull(message = "The roomId is required field!")
    private UUID roomId;

}
