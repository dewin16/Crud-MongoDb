package com.mongodb.mongodb.nombre.repositories;



import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mongodb.mongodb.nombre.entities.Hotels;

@Repository
public interface hotelsRepository extends MongoRepository<Hotels,Long> {

    Boolean existsByName(String name);
}
