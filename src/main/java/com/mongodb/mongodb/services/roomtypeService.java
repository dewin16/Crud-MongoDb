package com.mongodb.mongodb.services;

import java.util.List;
import java.util.Optional;


import com.mongodb.mongodb.entities.RoomType;

public interface roomtypeService {

    RoomType addRoomType(RoomType roomType);
    void deleteRoomtypes(Long id);
    Optional<RoomType> updateRoomtypes(Long id, RoomType roomType);
    RoomType getRoomtype(Long id);
    List<RoomType> getAllRoomtypes();

}
