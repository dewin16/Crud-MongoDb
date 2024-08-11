package com.mongodb.mongodb.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mongodb.mongodb.entities.RoomType;

@Repository
public interface roomtypeRepository extends MongoRepository<RoomType,Long> {
}
