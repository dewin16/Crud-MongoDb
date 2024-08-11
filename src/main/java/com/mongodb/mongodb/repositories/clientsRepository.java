package com.mongodb.mongodb.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mongodb.mongodb.entities.Clients;

@Repository
public interface clientsRepository extends MongoRepository<Clients, Long> {
}
