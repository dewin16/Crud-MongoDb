package com.mongodb.mongodb.services;

import java.util.List;
import java.util.Optional;

import com.mongodb.mongodb.entities.Hotels;

public interface hotelsService {

    Hotels addHotels(Hotels restaurants);
    void deleteHotels(Long id);
    Optional<Hotels> updateHotels(Long id, Hotels restaurants);
    Hotels getHotels(Long id);
    List<Hotels> getAllHotels();
}
