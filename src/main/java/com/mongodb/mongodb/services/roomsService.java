package com.mongodb.mongodb.services;

import java.util.List;
import java.util.Optional;


import com.mongodb.mongodb.entities.Rooms;

public interface roomsService {

    Rooms addRooms(Rooms rooms);
    void deleteRooms(Long id);
    Optional<Rooms> updateRooms(Long id, Rooms rooms);
    Rooms getRoom(Long id);
    List<Rooms> getAllRooms();
}
