package com.mongodb.mongodb.Crud.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mongodb.mongodb.Crud.entities.Clients;

@Repository
public interface clientsRepository extends MongoRepository<Clients, ObjectId> {

    Boolean existsByName(String name);
}
