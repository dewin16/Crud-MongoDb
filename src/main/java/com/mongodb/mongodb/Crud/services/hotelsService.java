package com.mongodb.mongodb.Crud.services;

import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.mongodb.Crud.entities.Hotels;

public interface hotelsService {

    Hotels addHotels(Hotels restaurants);
    void deleteHotels(ObjectId id);
    Hotels updateHotels(ObjectId id, Hotels restaurants);
    Hotels getHotels(ObjectId id);
    List<Hotels> getAllHotels();
    //Hotels updateReview(Long id);
}
