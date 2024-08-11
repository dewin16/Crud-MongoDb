package com.mongodb.mongodb.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mongodb.mongodb.entities.Hotels;

@Repository
public interface hotelsRepository extends MongoRepository<Hotels,Long> {
}
