package org.example.repositories;

import org.example.domain.Room;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class RoomRepositoryInMemory implements RoomRepository {
    Map<UUID, Room> rooms = new HashMap<>();
    public  RoomRepositoryInMemory(){
        var idByDay = UUID.fromString("141154cf-5158-4657-b17c-0a275d2d9545");
        var roomByDay =  new Room(idByDay, "day", 1000.00);
        var idByHour = UUID.fromString("6606e3f1-d486-46d6-9aa3-5223c3514432");
        var roomByHour =  new Room(idByHour, "hour", 100.00);
        this.rooms.put(idByDay, roomByDay);
        this.rooms.put(idByHour, roomByHour);
    }
    @Override
    public Room get(UUID roomId) {
        final Room room = this.rooms.get(roomId);
        if (room == null){
            throw new RuntimeException("Room Not Founded!");
        }
        return new Room(room.getRoomId(), room.getType(),room.getPrice());
    }
}
