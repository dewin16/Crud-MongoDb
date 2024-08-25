package com.mongodb.mongodb.Crud.repositories;



import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mongodb.mongodb.Crud.entities.Hotels;

@Repository
public interface hotelsRepository extends MongoRepository<Hotels,ObjectId> {

    Boolean existsByName(String name);
}
