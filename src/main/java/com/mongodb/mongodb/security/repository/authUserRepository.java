package com.mongodb.mongodb.security.repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mongodb.mongodb.security.entities.authUser;

@Repository
public interface authUserRepository extends MongoRepository<authUser, ObjectId> {

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Optional<authUser> findByUsernameOrEmail(String username, String email);
    

}
