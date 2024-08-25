package com.mongodb.mongodb.Crud.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mongodb.mongodb.Crud.entities.RoomType;

@Repository
public interface roomtypeRepository extends MongoRepository<RoomType,ObjectId> {

    Boolean existsByName(String name);
}
