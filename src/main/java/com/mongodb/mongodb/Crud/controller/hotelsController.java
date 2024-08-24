package com.mongodb.mongodb.nombre.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.mongodb.nombre.customQueries.CustomQueriesServiceImpl;
import com.mongodb.mongodb.nombre.entities.Hotels;
import com.mongodb.mongodb.nombre.services.hotelsServiceImp;

import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/hotels")
public class hotelsController {

    @Autowired
    private hotelsServiceImp service;

    @Autowired
    private CustomQueriesServiceImpl customQueriesServiceImpl;


    @GetMapping("/findAll")
    private List<Hotels> findAll(){

        return service.getAllHotels();

    }

    @GetMapping("/find/{id}")
    private Hotels findById(@PathVariable Long id){

        return service.getHotels(id);

    }

    @PostMapping("/add")
    private Hotels add(@RequestBody Hotels restaurant){

        return service.addHotels(restaurant);

    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<?> delete(@PathVariable Long id){

        try{
            service.deleteHotels(id);
            return ResponseEntity.ok().body("The restaurant was deleted");
        }catch(Exception e){
         return ResponseEntity.badRequest().body("Couldn't find the restaurant");   
        }
    
    }

    @PutMapping("update/{id}")
    
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Hotels hotel) {

        try{
            service.updateHotels(id, hotel);
            return ResponseEntity.ok().body("Updated");

        }catch(Exception e){
            return ResponseEntity.badRequest().body("Couldn't find the hotel");
        }

    }

    //this doesnt require a JSON on postman, just the text
    @PutMapping("{hotelId}/update/review/{reviewId}")
    public ResponseEntity<?> updateReview(@PathVariable Long hotelId, @PathVariable Long reviewId, @RequestBody String newReview){

        try {
            customQueriesServiceImpl.updateReviews(hotelId,reviewId,newReview);
            return ResponseEntity.ok().body(customQueriesServiceImpl.updateReviews(hotelId,reviewId,newReview));

        } catch (Exception e) {
           return ResponseEntity.badRequest().body("Couldn't find the review");
        }

    }
}
