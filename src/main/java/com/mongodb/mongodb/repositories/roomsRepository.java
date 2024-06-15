package com.mongodb.mongodb.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mongodb.mongodb.entities.Rooms;

@Repository
public interface roomsRepository extends MongoRepository<Rooms,Long> {
}
