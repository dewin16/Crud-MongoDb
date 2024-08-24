package com.mongodb.mongodb.nombre.services;

import java.util.List;

import com.mongodb.mongodb.nombre.entities.RoomType;

public interface roomtypeService {

    RoomType addRoomType(RoomType roomType);
    void deleteRoomtypes(Long id);
    RoomType updateRoomtypes(Long id, RoomType roomType);
    RoomType getRoomtype(Long id);
    List<RoomType> getAllRoomtypes();

}
