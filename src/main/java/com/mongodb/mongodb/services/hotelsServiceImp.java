package com.mongodb.mongodb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.mongodb.entities.Hotels;
import com.mongodb.mongodb.repositories.hotelsRepository;

@Service
public class hotelsServiceImp implements hotelsService {

    @Autowired
    private hotelsRepository repository;

    @Override
    public Hotels addHotels(Hotels restaurants) {

           return  repository.save(restaurants);
    }

    @Override
    public Optional<Hotels> updateHotels(Long id, Hotels restaurants) {
        Optional<Hotels> LFrestaurant = repository.findById(id);
        if(LFrestaurant.isPresent()){
            return Optional.of(repository.save(restaurants));
        }

        return LFrestaurant;

    }

    @Override
    public void deleteHotels(Long id) {
        Optional<Hotels> LFRestaurant  = repository.findById(id);
        if(LFRestaurant.isPresent()){
            Hotels RestaurantFound = LFRestaurant.get();
            repository.delete(RestaurantFound);
        } 

    }

    @Override
    public Hotels getHotels(Long  id) {
        Optional<Hotels> LFRestaurant = repository.findById(id);

        return LFRestaurant.get();

    }

    @Override
    public List<Hotels> getAllHotels() {
       
        return repository.findAll();
    }

}
