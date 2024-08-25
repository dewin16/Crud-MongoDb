package com.mongodb.mongodb.Crud.services;

import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.mongodb.Crud.entities.RoomType;

public interface roomtypeService {

    RoomType addRoomType(RoomType roomType);
    void deleteRoomtypes(ObjectId id);
    RoomType updateRoomtypes(ObjectId id, RoomType roomType);
    RoomType getRoomtype(ObjectId id);
    List<RoomType> getAllRoomtypes();

}
