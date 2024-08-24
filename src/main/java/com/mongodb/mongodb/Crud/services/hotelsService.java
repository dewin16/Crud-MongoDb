package com.mongodb.mongodb.nombre.services;

import java.util.List;

import com.mongodb.mongodb.nombre.entities.Hotels;

public interface hotelsService {

    Hotels addHotels(Hotels restaurants);
    void deleteHotels(Long id);
    Hotels updateHotels(Long id, Hotels restaurants);
    Hotels getHotels(Long id);
    List<Hotels> getAllHotels();
    //Hotels updateReview(Long id);
}
