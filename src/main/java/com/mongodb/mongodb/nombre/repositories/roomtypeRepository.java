package com.mongodb.mongodb.nombre.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mongodb.mongodb.nombre.entities.RoomType;

@Repository
public interface roomtypeRepository extends MongoRepository<RoomType,Long> {

    Boolean existsByName(String name);
}
