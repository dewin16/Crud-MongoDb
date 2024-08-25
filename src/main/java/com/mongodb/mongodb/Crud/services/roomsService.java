package com.mongodb.mongodb.Crud.services;

import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.mongodb.Crud.entities.Rooms;

public interface roomsService {

    Rooms addRooms(Rooms rooms);
    void deleteRooms(ObjectId id);
    Rooms updateRooms(ObjectId id, Rooms rooms);
    Rooms getRoom(ObjectId id);
    List<Rooms> getAllRooms();
}
