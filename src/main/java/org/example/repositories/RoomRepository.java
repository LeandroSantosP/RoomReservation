package org.example.repositories;

import org.example.domain.Room;

import java.util.UUID;

public interface RoomRepository {
    public Room get(UUID roomId);
}
