package com.mongodb.mongodb.nombre.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.mongodb.Constants.AppConstants;
import com.mongodb.mongodb.exceptions.alreadyExistException;
import com.mongodb.mongodb.exceptions.notFoundException;
import com.mongodb.mongodb.nombre.entities.Hotels;
import com.mongodb.mongodb.nombre.repositories.hotelsRepository;
import com.mongodb.mongodb.utils.operations;

@Service
public class hotelsServiceImp implements hotelsService {

    @Autowired
    private hotelsRepository repository;

    @Override
    public Hotels addHotels(Hotels hotels) {
        Long id = operations.autoIncrement(repository.findAll());
        Hotels hotelToSave = new
         Hotels(id, hotels.getName(),hotels.getAdress(),hotels.getRoomtypes(),
         hotels.getClients(), hotels.getReviews());
         
        if(!repository.existsByName(hotels.getName())){
            return  repository.save(hotelToSave);     

        }else{
            throw new alreadyExistException(AppConstants.ALREADY_EXIST);
        }
        
    }

    @Override
    public Hotels updateHotels(Long id, Hotels hotels) {
        Optional<Hotels> LFrestaurant = repository.findById(id);
        if(LFrestaurant.isPresent()){
            return repository.save(hotels);
        }

        throw new notFoundException(AppConstants.DOESNT_EXIST);

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

/* 
 
public Hotels updateReview(Long id, String newReview) {
    List<Hotels> hotels = repository.updateReviews(id);
    
    
    return hotels.get(0);
    
}
*/

}
