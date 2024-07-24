package com.mongodb.mongodb.nombre.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.mongodb.nombre.entities.Rooms;

@Repository
public interface roomsRepository extends MongoRepository<Rooms,Long> {

    @Query("{ 'type.name': ?0 }")
    Boolean existsByName(String name);
}
