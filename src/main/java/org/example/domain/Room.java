package org.example.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class Room {
    private final UUID roomId;
    private final String type;
    private final Double price;
}
