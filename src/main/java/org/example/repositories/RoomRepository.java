package org.example.repositories;

import org.example.domain.Room;
import org.springframework.stereotype.Repository;

import java.util.UUID;


public interface RoomRepository {
    public Room get(UUID roomId);
}
