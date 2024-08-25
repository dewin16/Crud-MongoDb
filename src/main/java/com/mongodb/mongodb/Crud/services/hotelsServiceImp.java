package com.mongodb.mongodb.Crud.services;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.mongodb.Constants.AppConstants;
import com.mongodb.mongodb.Crud.entities.Hotels;
import com.mongodb.mongodb.Crud.repositories.hotelsRepository;
import com.mongodb.mongodb.exceptions.alreadyExistException;
import com.mongodb.mongodb.exceptions.notFoundException;

@Service
public class hotelsServiceImp implements hotelsService {

    @Autowired
    private hotelsRepository repository;

    @Override
    public Hotels addHotels(Hotels hotels) {
        
         
        if(!repository.existsByName(hotels.getName())){
            return  repository.save(hotels);     

        }else{
            throw new alreadyExistException(AppConstants.ALREADY_EXIST);
        }
        
    }

    @Override
    public Hotels updateHotels(ObjectId id, Hotels hotels) {
        Optional<Hotels> LFrestaurant = repository.findById(id);
        if(LFrestaurant.isPresent()){
            return repository.save(hotels);
        }

        throw new notFoundException(AppConstants.DOESNT_EXIST);

    }

    @Override
    public void deleteHotels(ObjectId id) {
        Optional<Hotels> LFRestaurant  = repository.findById(id);
        if(LFRestaurant.isPresent()){
            Hotels RestaurantFound = LFRestaurant.get();
            repository.delete(RestaurantFound);
        } 

    }

    @Override
    public Hotels getHotels(ObjectId  id) {
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
