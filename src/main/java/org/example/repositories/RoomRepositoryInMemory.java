package org.example.repositories;

import org.example.domain.Room;

import java.util.*;

public class RoomRepositoryInMemory implements RoomRepository {
    Map<UUID, Room> rooms = new HashMap<>();
    public  RoomRepositoryInMemory(){
        var id = UUID.fromString("141154cf-5158-4657-b17c-0a275d2d9545");
        var roomByDay =  new Room(id, "day", 100.00);
        this.rooms.put(id,roomByDay );
    }
    @Override
    public Room get(UUID roomId) {
        final Room room = this.rooms.get(roomId);
        if (room == null){
            throw new RuntimeException("Room Not Founded!");
        }
        return room;
    }
}
