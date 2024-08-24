package com.mongodb.mongodb.nombre.services;

import java.util.List;

import com.mongodb.mongodb.nombre.entities.Rooms;

public interface roomsService {

    Rooms addRooms(Rooms rooms);
    void deleteRooms(Long id);
    Rooms updateRooms(Long id, Rooms rooms);
    Rooms getRoom(Long id);
    List<Rooms> getAllRooms();
}
