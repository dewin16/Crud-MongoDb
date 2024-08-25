package com.mongodb.mongodb.Crud.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.mongodb.Crud.entities.Rooms;

@Repository
public interface roomsRepository extends MongoRepository<Rooms,ObjectId> {

    @Query("{ 'type.name': ?0 }")
    Boolean existsByName(String name);
}
